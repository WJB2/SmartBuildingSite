package com.hozensoft.smartsite.money.service.impl;

import com.google.common.collect.Lists;
import com.hozensoft.smartsite.base.domain.BuildingSite;
import com.hozensoft.smartsite.base.dto.BuildingSiteDto;
import com.hozensoft.smartsite.base.dto.BuildingSiteItemDto;
import com.hozensoft.smartsite.base.dto.BuildingSiteQueryDto;
import com.hozensoft.smartsite.base.service.BuildingSiteService;
import com.hozensoft.smartsite.enumeration.PaymentStatusEnum;
import com.hozensoft.smartsite.money.dao.query.PaymentDetailQuery;
import com.hozensoft.smartsite.money.dao.query.PaymentQuery;
import com.hozensoft.smartsite.money.dao.repo.PaymentDetailRepo;
import com.hozensoft.smartsite.money.dao.repo.PaymentRepo;
import com.hozensoft.smartsite.money.domain.Payment;
import com.hozensoft.smartsite.money.domain.PaymentDetail;
import com.hozensoft.smartsite.money.dto.*;
import com.hozensoft.smartsite.money.service.PaymentService;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.system.core.dto.PositionDto;
import com.hozensoft.system.core.service.PositionService;
import com.hozensoft.system.utils.ContextUtils;
import com.hozensoft.system.utils.awared.CreatedAwared;
import com.hozensoft.system.utils.awared.UpdatedAwared;
import com.hozensoft.system.utils.bean.ContextHolder;
import com.hozensoft.utils.beans.BeanUtils;
import com.hozensoft.utils.persistent.IdGen;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    /**
     * 姓名列
     */
    public static final int NAME_COL_IDX = 1;

    /**
     * 身份证列
     */
    public static final int ID_CARD_NO_COL_IDX = 2;

    /**
     * 开户银行列
     */
    public static final int BANK_COL_IDX = 3;

    /**
     * 银行卡号列
     */
    public static final int BANK_NO_COL_IDX = 4;

    /**
     * 工种列
     */
    public static final int WORK_TYPE_COL_IDX = 5;

    /**
     * 出勤天数
     */
    public static final int ATTENCE_DAYS_COL_IDX = 6;
    /**
     * 出勤天数
     */
    public static final int ATTENCE_DAYS_LOST_COL_IDX = 7;

    /**
     * 出勤天数
     */
    public static final int ATTENCE_DAYS_ABNORMAL_COL_IDX = 8;

    /**
     * 出勤天数
     */
    public static final int ATTENCE_DAYS_NORMAL_COL_IDX = 9;

    /**
     * 工资列
     */
    public static final int MONEY_COL_IDX = 10;

    @Autowired
    private PaymentQuery paymentQuery;

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private PaymentDetailRepo paymentDetailRepo;

    @Autowired
    private PaymentDetailQuery paymentDetailQuery;

    @Autowired
    private BuildingSiteService buildingSiteService;

    @Autowired
    private PositionService positionService;


    @Override
    @Transactional
    public List<PaymentDetailItemDto> addPayment(ContextHolder holder, PaymentValueDto paymentDto, MultipartFile detailExcelFile) {

        Payment payment = new Payment();

        BeanUtils.copyProperties(paymentDto, payment);

        BuildingSite site = buildingSiteService.loadBuildingSiteById(holder, paymentDto.getBuildingSiteId());


        payment.setId(IdGen.generate());
        payment.setTenantId(holder.getTenantId());
        payment.setBuildingDeveloperId(site.getBuildingDeveloperId());

        Date date = new Date();
        DateUtils.setYears(date, paymentDto.getYearMonth()/100);
        DateUtils.setMonths(date, payment.getYearMonth()%100);
        DateUtils.setDays(date, 1);

        payment.setBeginDate(new Date(date.getTime()));

        DateUtils.addMonths(date, 1);
        DateUtils.addDays(date, -1);
        payment.setEndDate(new Date(date.getTime()));

        payment.setMoney(BigDecimal.ZERO);
        payment.setDeletedFlag(false);
        PositionDto positionDto = positionService.findPositionById(holder, paymentDto.getCurrentPositionId());
        ContextUtils.applyAwaredContext(positionDto, payment, CreatedAwared.class, UpdatedAwared.class);

        List<PaymentDetail> paymentDetailList = readPaymentDetailsFromExcel(payment, detailExcelFile);

        List<PaymentDetail> oldPaymentDetail = paymentDetailRepo.loadPaymentDetailList(holder.getTenantId(), site.getBuildingDeveloperId(), site.getId(),
                paymentDetailList.stream().map(item->item.getIdCardNo()).collect(Collectors.toList()), paymentDto.getYearMonth());

        Map<String, PaymentDetail> oldPaymentDetailHash = oldPaymentDetail.stream().collect(Collectors.toMap(item->item.getIdCardNo() + ":" + item.getName(), item->item));

        paymentDetailList.stream().filter((item)->{
            if(oldPaymentDetailHash.containsKey(item.getIdCardNo() + ":" + item.getName())){

                if(PaymentStatusEnum.SUBMITTED.equals(oldPaymentDetailHash.get(item.getIdCardNo() + ":" + item.getName()).getStatus())){
                    paymentDetailRepo.editPaymentDetailMoney(holder.getTenantId(), oldPaymentDetailHash.get(item.getIdCardNo() + ":" + item.getName()).getId(), item.getMoney());
                }else{
                    throw new RuntimeException(item.getName() + "(" + item.getIdCardNo() + ")的工资不能修改");
                }

                return false;
            }else{
                return true;
            }
        }).forEach((paymentDetail -> {
            payment.setMoney(payment.getMoney().add(paymentDetail.getMoney()));
            ContextUtils.applyAwaredContext(positionDto, paymentDetail, CreatedAwared.class, UpdatedAwared.class);
        }));


        List<List<PaymentDetail>> paymentDetailListWrapper = Lists.partition(paymentDetailList, 50);

        paymentRepo.addPayment(payment);
        paymentDetailListWrapper.stream().forEach((list)->{
            paymentDetailRepo.addPaymentDetailList(list);
        });

        PaymentDetailQueryDto params = PaymentDetailQueryDto.
                builder().tenantId(holder.getTenantId()).
                buildingDeveloperId(Arrays.asList(payment.getBuildingDeveloperId())).
                buildingSiteId(Arrays.asList(payment.getBuildingSiteId())).
                yearMonth(payment.getYearMonth()).build();

        return findPaymentDetail(holder, params, new Limitable());
    }

    @Override
    @Transactional
    public List<PaymentDetailItemDto> payoffPayment(ContextHolder holder, PaymentValueDto paymentDto, MultipartFile detailExcelFile) {

        BuildingSite site = buildingSiteService.loadBuildingSiteById(holder, paymentDto.getBuildingSiteId());

        List<String> idCardNoList = readAllIdCardNoFromExcel(detailExcelFile);

        paymentDetailRepo.editPaymentDetailStatus(holder.getTenantId(), site.getBuildingDeveloperId(),
                site.getId(), idCardNoList, paymentDto.getYearMonth(), PaymentStatusEnum.PAID);

        PaymentDetailQueryDto params = PaymentDetailQueryDto.
                builder().tenantId(holder.getTenantId()).
                buildingDeveloperId(Arrays.asList(site.getBuildingDeveloperId())).
                buildingSiteId(Arrays.asList(site.getId())).
                yearMonth(paymentDto.getYearMonth()).build();

        return findPaymentDetail(holder, params, new Limitable());
    }

    @Override
    @Transactional
    public XSSFWorkbook generatePaymentExcel(ContextHolder holder, PaymentDetailQueryDto params) {

        BuildingSiteDto buildingSite = buildingSiteService.findBuildingSiteById(holder, params.getBuildingSiteId().get(0));

        List<PaymentDetailItemDto> list = findPaymentDetail(holder, params, new Limitable());

        String name = buildingSite.getBuildingDeveloper().getName() + "的" + buildingSite.getName() + "工程第" +
                params.getYearMonth() + "期工资发放详情";

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet(name);

        XSSFRow nameRow = sheet.createRow(0);
        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        nameRow.createCell(0).setCellValue(name);
        nameRow.getCell(0).setCellStyle(style);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));

        XSSFRow rowTitle = sheet.createRow(1);
        rowTitle.createCell(0).setCellValue("序号");
        rowTitle.createCell(NAME_COL_IDX).setCellValue("姓名");
        rowTitle.createCell(ID_CARD_NO_COL_IDX).setCellValue("身份证号");
        rowTitle.createCell(BANK_COL_IDX).setCellValue("开户银行");
        rowTitle.createCell(BANK_NO_COL_IDX).setCellValue("银行卡号");
        rowTitle.createCell(WORK_TYPE_COL_IDX).setCellValue("工种");
        rowTitle.createCell(ATTENCE_DAYS_COL_IDX).setCellValue("出勤天数");
        rowTitle.createCell(MONEY_COL_IDX).setCellValue("应发工资");

        for(int i=0; i<list.size(); i++){
            XSSFRow row = sheet.createRow(i+2);
            PaymentDetailItemDto detail = list.get(i);

            row.createCell(0).setCellValue(0);
            row.createCell(NAME_COL_IDX).setCellValue(detail.getName());
            row.createCell(ID_CARD_NO_COL_IDX).setCellValue(detail.getIdCardNo());
            row.createCell(BANK_COL_IDX).setCellValue(detail.getBank());
            row.createCell(BANK_NO_COL_IDX).setCellValue(detail.getBankNo());
            row.createCell(WORK_TYPE_COL_IDX).setCellValue(detail.getWorkType());
            row.createCell(ATTENCE_DAYS_COL_IDX).setCellValue(detail.getAttenceDays().doubleValue());
            row.createCell(MONEY_COL_IDX).setCellValue(detail.getMoney().doubleValue());
        }

        list = list.stream().filter(item->{
            return !PaymentStatusEnum.PAID.equals(item.getStatus());
        }).collect(Collectors.toList());

        if(list.size()>0){

            paymentDetailRepo.editPaymentDetailStatus(holder.getTenantId(), buildingSite.getBuildingDeveloperId(),
                    buildingSite.getId(), list.stream().map((item)->{return item.getIdCardNo();}).collect(Collectors.toList()), params.getYearMonth(), PaymentStatusEnum.PROCESSING);
        }

        return wb;
    }

    @Override
    public List<PaymentDetailItemDto> findPaymentDetail(ContextHolder holder, PaymentDetailQueryDto params, Limitable limit){

        params.setTenantId(holder.getTenantId());

        return paymentDetailQuery.findPaymentDetailList(params);
    }

    @Override
    public List<PaymentReportItemDto> paymentDetailReportByYear(ContextHolder holder, Integer year) {

        BuildingSiteQueryDto buildingSiteParams = BuildingSiteQueryDto.builder().tenantId(holder.getTenantId()).build();

        List<BuildingSiteItemDto> buildingSiteList = buildingSiteService.findBuildingSiteList(holder, buildingSiteParams, new Limitable());

        List<PaymentReportCellDto> list = paymentDetailQuery.findPaymentReportCellList(holder.getTenantId(), year);

        List<PaymentReportItemDto> paymentReportList = buildingSiteList.stream().map((item)->{
            PaymentReportItemDto paymentReport = new PaymentReportItemDto();
            paymentReport.setBuildingDeveloper(item.getBuildingDeveloper());
            paymentReport.setBuildingSite(item);

            Map<Integer, PaymentReportCellDto> hash = new HashMap<>();

            list.stream().filter((cell)->{
                return cell.getBuildingSiteId().equals(item.getId());
            }).forEach((cell)->{
                hash.put(cell.getYearMonth(), cell);
            });

            paymentReport.setMonthReports(hash);

            return paymentReport;
        }).collect(Collectors.toList());

        return paymentReportList;
    }

    protected List<String> readAllIdCardNoFromExcel(MultipartFile detailExcelFile){
        List<String> paymentDetailList = new LinkedList<>();

        XSSFWorkbook xssfWorkbook = null;

        try{
            xssfWorkbook = new XSSFWorkbook(detailExcelFile.getInputStream());
        }catch (Exception ex){
            throw new RuntimeException();
        }

        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // 对于每个sheet，读取其中的每一行
            for (int rowNum = 2; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow == null) {
                    continue;
                }

                paymentDetailList.add(xssfRow.getCell(ID_CARD_NO_COL_IDX).getStringCellValue().trim());
            }
        }

        return paymentDetailList;
    }

    protected List<PaymentDetail> readPaymentDetailsFromExcel(Payment payment, MultipartFile detailExcelFile){

        List<PaymentDetail> paymentDetailList = new LinkedList<>();

        XSSFWorkbook xssfWorkbook = null;

        try{
            xssfWorkbook = new XSSFWorkbook(detailExcelFile.getInputStream());
        }catch (Exception ex){
            throw new RuntimeException();
        }

        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // 对于每个sheet，读取其中的每一行
            for (int rowNum = 2; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow == null) {
                    continue;
                }

                PaymentDetail detail = new PaymentDetail();

                detail.setId(IdGen.generate());
                detail.setTenantId(payment.getTenantId());
                detail.setBuildingDeveloperId(payment.getBuildingDeveloperId());
                detail.setBuildingSiteId(payment.getBuildingSiteId());
                detail.setName(xssfRow.getCell(NAME_COL_IDX).getStringCellValue().trim());
                detail.setIdCardNo(xssfRow.getCell(ID_CARD_NO_COL_IDX).getStringCellValue().trim());
                detail.setBank(xssfRow.getCell(BANK_COL_IDX).getStringCellValue().trim());
                detail.setBankNo(xssfRow.getCell(BANK_NO_COL_IDX).getStringCellValue().trim());
                detail.setWorkType(xssfRow.getCell(WORK_TYPE_COL_IDX)!=null?
                        xssfRow.getCell(WORK_TYPE_COL_IDX).getStringCellValue().trim():"");
                detail.setAttenceDays(BigDecimal.valueOf(Double.valueOf(xssfRow.getCell(ATTENCE_DAYS_COL_IDX).getRawValue())));
                detail.setMoney(BigDecimal.valueOf(Double.valueOf(xssfRow.getCell(MONEY_COL_IDX).getRawValue())));
                detail.setYearMonth(payment.getYearMonth());
                detail.setBeginDate(payment.getBeginDate());
                detail.setEndDate(payment.getEndDate());
                detail.setStatus(PaymentStatusEnum.SUBMITTED);

                paymentDetailList.add(detail);
            }
        }

        return paymentDetailList;
    }
}
