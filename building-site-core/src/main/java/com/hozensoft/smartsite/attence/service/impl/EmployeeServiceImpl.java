package com.hozensoft.smartsite.attence.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hozensoft.smartsite.attence.dao.query.AttenceRecordQuery;
import com.hozensoft.smartsite.attence.dao.query.EmployeeQuery;
import com.hozensoft.smartsite.attence.dao.repo.EmployeeRepo;
import com.hozensoft.smartsite.attence.domain.Employee;
import com.hozensoft.smartsite.attence.dto.*;
import com.hozensoft.smartsite.attence.service.AttenceRecordService;
import com.hozensoft.smartsite.attence.service.EmployeeService;
import com.hozensoft.smartsite.attence.transfermer.EmployeeTransformer;
import com.hozensoft.smartsite.base.domain.BuildingSite;
import com.hozensoft.smartsite.base.dto.BuildingSiteDto;
import com.hozensoft.smartsite.base.dto.EmployeeWechatReportDto;
import com.hozensoft.smartsite.base.service.BuildingSiteService;
import com.hozensoft.smartsite.enumeration.PaymentStatusEnum;
import com.hozensoft.smartsite.event.dispatcher.EmployeeDispatcher;
import com.hozensoft.smartsite.money.dto.PaymentDetailItemDto;
import com.hozensoft.smartsite.money.dto.PaymentDetailQueryDto;
import com.hozensoft.smartsite.money.service.PaymentService;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.struct.pagehelper.Pageable;
import com.hozensoft.system.core.dto.PositionDto;
import com.hozensoft.system.core.service.PositionService;
import com.hozensoft.system.utils.ContextUtils;
import com.hozensoft.system.utils.awared.CreatedAwared;
import com.hozensoft.system.utils.awared.UpdatedAwared;
import com.hozensoft.system.utils.bean.ContextHolder;
import com.hozensoft.utils.date.DateParseUtils;
import com.hozensoft.utils.persistent.IdGen;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    /**
     * 姓名列
     */
    public static final int NAME_COL_IDX = 1;

    /**
     * 工号列
     */
    public static final int CODE_COL_IDX = 2;

    /**
     * 身份证号列
     */
    public static final int ID_CARD_NO_COL_IDX = 3;

    /**
     * 开户行列
     */
    public static final int BANK_COL_IDX = 4;

    /**
     * 银行卡号列
     */
    public static final int BANK_NO_COL_IDX = 5;

    /**
     * 工种列
     */
    public static final int WORK_TYPE_COL_IDX = 6;

    /**
     * 手机号列
     */
    public static final int MOBILE_COL_IDX = 7;

    /**
     * 联系地址列
     */
    public static final int ADDRESS_COL_IDX = 8;
    /**
     * 入职日期列
     */
    public static final int REGISTER_TIME_COL_IDX = 9;


    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private EmployeeQuery employeeQuery;

    @Autowired
    private PositionService positionService;

    @Autowired
    private BuildingSiteService buildingSiteService;

    @Autowired
    private EmployeeDispatcher employeeDispatcher;

    @Autowired
    private AttenceRecordQuery attenceRecordQuery;

    @Autowired
    private PaymentService paymentService;

    @Override
    public EmployeeDto addEmployee(ContextHolder holder, EmployeeValueDto employeeDto) {

        Employee employee = EmployeeTransformer.transformEmployeeAddDtoToDomain(employeeDto);
        employee.setTenantId(holder.getTenantId());

        PositionDto currentPosition = positionService.findPositionById(holder, employeeDto.getCurrentPositionId());
        ContextUtils.applyAwaredContext(currentPosition, employee, CreatedAwared.class, UpdatedAwared.class);

        BuildingSite buildingSite = buildingSiteService.loadBuildingSiteById(holder, employeeDto.getBuildingSiteId());
        employee.setBuildingDeveloperId(buildingSite.getBuildingDeveloperId());

        employeeRepo.addEmployee(employee);
        EmployeeDto outDto = findEmployeeById(holder, employee.getId());

        employeeDispatcher.dispatchEmployeeCreatedEvent(outDto);

        return outDto;
    }

    @Override
    public EmployeeDto editEmployee(ContextHolder holder, EmployeeValueDto employeeDto) {

        Employee employee = EmployeeTransformer.transformEmployeeEditDtoToDomain(employeeDto);

        employee.setBuildingDeveloperId(null);
        employee.setBuildingSiteId(null);

        PositionDto currentPosition = positionService.findPositionById(holder, employeeDto.getCurrentPositionId());
        ContextUtils.applyAwaredContext(currentPosition, employee, UpdatedAwared.class);

        employeeRepo.patchEditEmployee(employee);
        EmployeeDto outDto = findEmployeeById(holder, employee.getId());

        return outDto;
    }

    @Override
    public void deleteEmployeeById(ContextHolder holder, String employeeId) {

        //employeeRepo.deleteEmployeeById(tenantId, employeeId);

        Employee employee = employeeRepo.loadEmployeeById(holder.getTenantId(), employeeId);

        employee.setDeletedFlag(!employee.getDeletedFlag());
        employee.setFingerprintRegistered(false);
        employee.setFaceRegistered(false);

        employeeRepo.patchEditEmployee(employee);

        EmployeeDto outDto = findEmployeeById(holder, employee.getId());

        if(employee.getDeletedFlag()){
            employeeDispatcher.dispatchEmployeeDeletedEvent(outDto);
        }else{
            employeeDispatcher.dispatchEmployeeCreatedEvent(outDto);
        }
    }

    @Override
    public Employee loadEmployeeById(ContextHolder holder, String employeeId) {

        return employeeRepo.loadEmployeeById(holder.getTenantId(), employeeId);
    }

    @Override
    public Employee loadEmployeeByCode(ContextHolder holder, String buildingDeveloperId, String buildingSiteId, String employeeCode) {

        return employeeRepo.loadEmployeeByCode(holder.getTenantId(), buildingDeveloperId, buildingSiteId, employeeCode);
    }

    @Override
    public EmployeeDto findEmployeeById(ContextHolder holder, String employeeId) {

        EmployeeDto bdd = employeeQuery.findEmployeeById(employeeId);

        return bdd;
    }

    @Override
    public EmployeeDto findEmployeeByCode(ContextHolder holder, String buildingDeveloperId, String employeeCode) {
        return employeeQuery.findEmployeeByCode(buildingDeveloperId, employeeCode);
    }

    @Override
    public List<EmployeeItemDto> findEmployeeList(ContextHolder holder, EmployeeQueryDto params, Limitable limitable) {

        params.setTenantId(holder.getTenantId());

        PageHelper.offsetPage(limitable.getOffset(), limitable.getLimit());

        return employeeQuery.findEmployeeList(params);
    }

    @Override
    public PageInfo<EmployeeItemDto> findEmployeePage(ContextHolder holder, EmployeeQueryDto params, Pageable pageable) {

        params.setTenantId(holder.getTenantId());

        PageHelper.startPage(pageable.getPage(), pageable.getPageSize());
        List<EmployeeItemDto> list = employeeQuery.findEmployeeList(params);

        return new PageInfo<>(list);
    }

    @Override
    public EmployeeWechatReportDto reportEmployeeAttenceRecordAndSalary(ContextHolder holder, String employeeId, Date beginDate, Date endDate) {
        AttenceRecordQueryDto params = AttenceRecordQueryDto.builder().tenantId(holder.getTenantId()).
                employeeId(Arrays.asList(employeeId)).beginDate(beginDate).endDate(endDate).build();
        List<AttenceRecordItemDto> attenceReocrds = attenceRecordQuery.findAttenceRecordList(params);

        Set<String> buildingSiteIdSet = attenceReocrds.stream().map((item)->{
            return item.getBuildingSiteId();
        }).collect(Collectors.toSet());

        List<EmployeeReportBySiteDto> list = new ArrayList<>(buildingSiteIdSet.size());

        buildingSiteIdSet.stream().forEach((buildingSiteId)->{
            BuildingSiteDto buildingSiteDto = buildingSiteService.findBuildingSiteById(holder, buildingSiteId);

            EmployeeReportBySiteDto reportDto = new EmployeeReportBySiteDto();
            reportDto.setBuildingSite(buildingSiteDto);
            reportDto.setAttenceRecords(attenceReocrds.stream().filter((item->item.getBuildingSiteId().equals(buildingSiteId))).collect(Collectors.toList()));

            list.add(reportDto);
        });

        PaymentDetailQueryDto paymentParams = PaymentDetailQueryDto.builder().tenantId(holder.getTenantId()).
                yearMonth(beginDate.getYear()*100+beginDate.getMonth()).employeeId(employeeId).build();

        List<PaymentDetailItemDto> items = paymentService.findPaymentDetail(holder, paymentParams, new Limitable());

        items.forEach(item->{
            list.forEach((report)->{
                if(item.getBuildingSiteId().equals(report.getBuildingSite().getId())){
                    if(item.getStatus().equals(PaymentStatusEnum.PAID)){
                        report.setSalary(item.getMoney());
                    }
                }
            });
        });

        EmployeeWechatReportDto reportDto = new EmployeeWechatReportDto();
        reportDto.setReports(list);
        reportDto.setEmployee(findEmployeeById(holder, employeeId));

        return reportDto;
    }

    public void importEmployee(ContextHolder holder, EmployeeValueDto employeeDto, MultipartFile detailExcelFile) {

        BuildingSite site = buildingSiteService.loadBuildingSiteById(holder, employeeDto.getBuildingSiteId());

        List<EmployeeValueDto> employeeList = readEmployeesForExcel(site, detailExcelFile, employeeDto.getCurrentPositionId());

        for (int i = 0; i < employeeList.size(); i++) {
            addEmployee(holder, employeeList.get(i));
        }

    }

    protected List<EmployeeValueDto> readEmployeesForExcel(BuildingSite site, MultipartFile detailExcelFile, String currentPositionId) {
        List<EmployeeValueDto> employeeList = new LinkedList<>();

        XSSFWorkbook xssfWorkbook = null;
        try {
            xssfWorkbook = new XSSFWorkbook(detailExcelFile.getInputStream());
        } catch (Exception ex) {
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

                EmployeeValueDto emp = new EmployeeValueDto();


                emp.setId(IdGen.generate());
                emp.setTenantId(site.getTenantId());
                emp.setBuildingDeveloperId(site.getBuildingDeveloperId());
                emp.setBuildingSiteId(site.getId());
                xssfRow.getCell(NAME_COL_IDX).setCellType(CellType.STRING);
                emp.setName(xssfRow.getCell(NAME_COL_IDX).getStringCellValue().trim());

                xssfRow.getCell(CODE_COL_IDX).setCellType(CellType.STRING);
                emp.setCode(xssfRow.getCell(CODE_COL_IDX).getStringCellValue().trim());

                xssfRow.getCell(ID_CARD_NO_COL_IDX).setCellType(CellType.STRING);
                emp.setIdCardNo(xssfRow.getCell(ID_CARD_NO_COL_IDX).getStringCellValue().trim());

                xssfRow.getCell(BANK_COL_IDX).setCellType(CellType.STRING);
                emp.setBank(xssfRow.getCell(BANK_COL_IDX).getStringCellValue().trim());

                xssfRow.getCell(BANK_NO_COL_IDX).setCellType(CellType.STRING);
                emp.setBankNo(xssfRow.getCell(BANK_NO_COL_IDX).getStringCellValue().trim());

                if(xssfRow.getCell(WORK_TYPE_COL_IDX) != null){
                    xssfRow.getCell(WORK_TYPE_COL_IDX).setCellType(CellType.STRING);
                }
                emp.setWorkType(xssfRow.getCell(WORK_TYPE_COL_IDX) != null ?
                        xssfRow.getCell(WORK_TYPE_COL_IDX).getStringCellValue().trim() : "");

                if(xssfRow.getCell(MOBILE_COL_IDX)!=null){
                    xssfRow.getCell(MOBILE_COL_IDX).setCellType(CellType.STRING);
                }
                emp.setMobile(xssfRow.getCell(MOBILE_COL_IDX)!=null?xssfRow.getCell(MOBILE_COL_IDX).getStringCellValue().trim():"");

                if(xssfRow.getCell(ADDRESS_COL_IDX)!=null){
                    xssfRow.getCell(ADDRESS_COL_IDX).setCellType(CellType.STRING);
                }
                emp.setAddress(xssfRow.getCell(ADDRESS_COL_IDX)!=null?xssfRow.getCell(ADDRESS_COL_IDX).getStringCellValue().trim():"");

                emp.setRegisterTime(xssfRow.getCell(REGISTER_TIME_COL_IDX)!=null?xssfRow.getCell(REGISTER_TIME_COL_IDX).getDateCellValue():null);

                emp.setCurrentPositionId(currentPositionId);

                employeeList.add(emp);
            }
        }

        return employeeList;
    }
}
