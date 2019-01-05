package com.hozensoft.smartsite.attence.service.impl;

import com.hozensoft.smartsite.attence.dao.query.AttenceRecordQuery;
import com.hozensoft.smartsite.attence.dao.query.EmployeeQuery;
import com.hozensoft.smartsite.attence.dao.repo.AttenceRecordRepo;
import com.hozensoft.smartsite.attence.domain.AttenceRecord;
import com.hozensoft.smartsite.attence.domain.AttenceRule;
import com.hozensoft.smartsite.attence.dto.*;
import com.hozensoft.smartsite.attence.service.AttenceRecordService;
import com.hozensoft.smartsite.attence.service.AttenceRuleService;
import com.hozensoft.smartsite.base.dao.query.BuildingSiteQuery;
import com.hozensoft.smartsite.base.dto.BuildingSiteDto;
import com.hozensoft.smartsite.base.dto.BuildingSiteItemDto;
import com.hozensoft.smartsite.base.dto.BuildingSiteQueryDto;
import com.hozensoft.smartsite.base.service.BuildingSiteService;
import com.hozensoft.smartsite.enumeration.CheckStatusEnum;
import com.hozensoft.smartsite.enumeration.ClockTypeEnum;
import com.hozensoft.smartsite.iclock.dto.IClockRecordDto;
import com.hozensoft.smartsite.money.dto.PaymentDetailItemDto;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.system.core.dto.PositionDto;
import com.hozensoft.system.core.service.PositionService;
import com.hozensoft.system.utils.ContextUtils;
import com.hozensoft.system.utils.awared.UpdatedAwared;
import com.hozensoft.system.utils.bean.ContextHolder;
import com.hozensoft.utils.date.DateParseUtils;
import com.hozensoft.utils.persistent.IdGen;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AttenceRecordServiceImpl implements AttenceRecordService {

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
     * 缺卡天数
     */
    public static final int ATTENCE_DAYS_LOST_COL_IDX = 7;

    /**
     * 迟到早退天数
     */
    public static final int ATTENCE_DAYS_ABNORMAL_COL_IDX = 8;

    /**
     * 正常出勤天数
     */
    public static final int ATTENCE_DAYS_NORMAL_COL_IDX = 9;

    /**
     * 工资列
     */
    public static final int MONEY_COL_IDX = 10;

    @Autowired
    private BuildingSiteQuery buildingSiteQuery;

    @Autowired
    private EmployeeQuery employeeQuery;

    @Autowired
    private AttenceRecordQuery attenceRecordQuery;

    @Autowired
    private AttenceRecordRepo attenceRecordRepo;

    @Autowired
    private PositionService positionService;

    @Autowired
    private AttenceRuleService attenceRuleService;

    @Autowired
    private BuildingSiteService buildingSiteService;

    @Override
    public List<BuildingSiteWithEmployeeDto> findEmployeeForManualClocking(ContextHolder holder, String buildingDeveloperId, String staffId) {
        BuildingSiteQueryDto buildingSiteQueryParams = BuildingSiteQueryDto.builder().
                tenantId(holder.getTenantId()).
                buildingDeveloperId(Arrays.asList(buildingDeveloperId)).
                adminStaffId(Arrays.asList(staffId)).deletedFlag(false).build();

        List<BuildingSiteItemDto> buildingSites = buildingSiteQuery.findBuildingSiteList(buildingSiteQueryParams);
        Map<String, AttenceRuleItemDto> rulesMap = attenceRuleService.findAttenceRuleList(holder, buildingDeveloperId).stream().collect(Collectors.toMap(item->item.getBuildingSiteId(), item->item));

        List<String> buildingSiteIds = buildingSites.stream().map(buildingSite->(buildingSite.getId())).collect(Collectors.toList());

        EmployeeQueryDto employeeQueryParams = EmployeeQueryDto.builder().
                tenantId(holder.getTenantId()).
                buildingDeveloperId(Arrays.asList(buildingDeveloperId)).
                buildingSiteId(buildingSiteIds).deletedFlag(false).build();

        List<EmployeeItemDto> employees = employeeQuery.findEmployeeList(employeeQueryParams);

        List<BuildingSiteWithEmployeeDto> result = buildingSites.stream().map((buildingSite)->{
            BuildingSiteWithEmployeeDto buildingSiteWithEmployee = new BuildingSiteWithEmployeeDto();

            buildingSiteWithEmployee.setRule(rulesMap.get(buildingSite.getId()));
            buildingSiteWithEmployee.setBuildingSite(buildingSite);
            buildingSiteWithEmployee.setEmployees(employees.stream().
                    filter(employee->buildingSite.getId().equals(employee.getBuildingSiteId())).
                    collect(Collectors.toList()));

            return buildingSiteWithEmployee;
        }).collect(Collectors.toList());

        return result.stream().filter(item->item.getEmployees().size()>0).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void clocking(ContextHolder holder, String positionId, EmployeeItemDto employee) {

        Date checkDate = DateParseUtils.parseDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));

        AttenceRecord record = attenceRecordRepo.loadAttenceByEmployeeAndCheckDate(holder.getTenantId(),
                checkDate, employee.getBuildingDeveloperId(), employee.getBuildingSiteId(), employee.getId());
        AttenceRule rule = attenceRuleService.loadAttenceRuleByBuildingSiteId(holder, employee.getBuildingDeveloperId(), employee.getBuildingSiteId());

        if(rule==null){
            rule = AttenceRule.buildDefault(holder.getTenantId(), employee.getBuildingDeveloperId(), employee.getBuildingSiteId());
        }

        if(record==null){
            record = new AttenceRecord();
            record.init(rule, employee, checkDate);
        }

        PositionDto positionDto = positionService.findPositionById(holder, positionId);

        ContextUtils.applyAwaredContext(positionDto, record, UpdatedAwared.class);

        IClockRecordDto item = new IClockRecordDto();
        item.setType(ClockTypeEnum.MANNUAL);
        item.setClockTime(new Date());

        record.clock(item);

        if(StringUtils.isBlank(record.getId())){
            record.setId(IdGen.generate());
            attenceRecordRepo.addAttenceRecord(record);
        }else {
            attenceRecordRepo.editAttenceRecord(record);
        }
    }

    @Override
    @Transactional
    public void repairClocking(ContextHolder holder, AttenceRecordRepairDto repairDto) {

        Date checkDate = DateParseUtils.parseDate(DateFormatUtils.format(repairDto.getCheckInTime()!=null?
                repairDto.getCheckInTime():repairDto.getCheckOutTime(), "yyyy-MM-dd"));

        AttenceRule rule = attenceRuleService.loadAttenceRuleByBuildingSiteId(holder, repairDto.getBuildingDeveloperId(), repairDto.getBuildingSiteId());
        final PositionDto positionDto = positionService.findPositionById(holder, holder.getPositionId());

        if(rule==null){
            rule =AttenceRule.buildDefault(holder.getTenantId(), repairDto.getBuildingDeveloperId(), repairDto.getBuildingSiteId());
        }

        final AttenceRule r = rule;

        repairDto.getEmployeeIdList().forEach((employeeId)->{
            AttenceRecord record = attenceRecordRepo.loadAttenceByEmployeeAndCheckDate(holder.getTenantId(),
                    checkDate, repairDto.getBuildingDeveloperId(), repairDto.getBuildingSiteId(), employeeId);

            if(record==null){
                record = new AttenceRecord();
                record.setTenantId(holder.getTenantId());
                record.setBreakEnabled(r.getBreakEnabled());
                record.setBeginTime(DateParseUtils.parseDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd") + " " + r.getBeginTime() + ":00"));
                record.setBreakBeginTime(DateParseUtils.parseDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd") + " " + r.getBreakBeginTime() + ":00"));
                record.setBreakEndTime(DateParseUtils.parseDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd") + " " + r.getBreakEndTime() + ":00"));
                record.setEndTime(DateParseUtils.parseDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd") + " " + r.getEndTime() + ":00"));
                record.setBuildingDeveloperId(repairDto.getBuildingDeveloperId());
                record.setBuildingSiteId(repairDto.getBuildingSiteId());
                record.setEmployeeId(employeeId);
                record.setCheckDate(checkDate);
            }

            ContextUtils.applyAwaredContext(positionDto, record, UpdatedAwared.class);

            if(repairDto.getCheckInTime()!=null){
                record.setCheckInTime(repairDto.getCheckInTime());
            }

            if(repairDto.getBreakCheckOutTime()!=null){
                record.setBreakCheckOutTime(repairDto.getBreakCheckOutTime());
            }

            if(repairDto.getBreakCheckInTime()!=null){
                record.setBreakCheckInTime(repairDto.getBreakCheckInTime());
            }

            if(repairDto.getCheckOutTime()!=null){
                record.setCheckOutTime(repairDto.getCheckOutTime());
            }

            if(StringUtils.isBlank(record.getId())){
                record.setId(IdGen.generate());
                attenceRecordRepo.addAttenceRecord(record);
            }else {
                attenceRecordRepo.editAttenceRecord(record);
            }
        });
    }

    @Override
    public List<BuildingSiteWithCheckDetail> findBuildingSiteCheckDetails(ContextHolder holder, AttenceRecordQueryDto params) {

        BuildingSiteQueryDto buildingSiteQueryParams = BuildingSiteQueryDto.builder().
                buildingDeveloperId(params.getBuildingDeveloperId()).
                id(params.getBuildingSiteId()).
                deletedFlag(false).build();

        List<BuildingSiteItemDto> buildingSites = buildingSiteQuery.findBuildingSiteList(buildingSiteQueryParams);

        List<String> buildingSiteIds = buildingSites.stream().map(buildingSite->(buildingSite.getId())).collect(Collectors.toList());

        params.setBuildingSiteId(buildingSiteIds);

        EmployeeQueryDto employeeQueryParams = EmployeeQueryDto.builder().
                buildingDeveloperId(params.getBuildingDeveloperId()).
                buildingSiteId(buildingSiteIds).build();

        List<EmployeeItemDto> employees = employeeQuery.findEmployeeList(employeeQueryParams);

        List<AttenceRecordItemDto> checkRecords = attenceRecordQuery.findAttenceRecordList(params);

        List<EmployeeWithCheckDetailDto> empList = employees.stream().map((employee)->{
            EmployeeWithCheckDetailDto emp = new EmployeeWithCheckDetailDto();

            emp.setEmployee(employee);
            emp.setCheckRecords(new HashMap<>());

            return emp;
        }).collect(Collectors.toList());

        Map<String, EmployeeWithCheckDetailDto> empHash = empList.stream().collect(Collectors.toMap((item)->{
            return item.getEmployee().getId();
        }, (item)->(item)));

        checkRecords.stream().forEach((checkRecord)->{
            EmployeeWithCheckDetailDto emp = empHash.get(checkRecord.getEmployeeId());

            if(emp==null){
                return;
            }

            emp.getCheckRecords().put(DateFormatUtils.format(checkRecord.getCheckDate(), "yyyyMMdd"), checkRecord);
        });

        List<BuildingSiteWithCheckDetail> result = buildingSites.stream().map((buildingSite)->{
            BuildingSiteWithCheckDetail site = new BuildingSiteWithCheckDetail();

            site.setBuildingSite(buildingSite);
            site.setEmployees(empList.stream().filter((employee)->(buildingSite.getId().equals(employee.getEmployee().getBuildingSiteId()))).collect(Collectors.toList()));

            return site;
        }).collect(Collectors.toList());

        return result;
    }

    @Override
    public XSSFWorkbook generateSalaryReportExcel(ContextHolder holder, AttenceRecordQueryDto params) {

        BuildingSiteDto buildingSite = buildingSiteService.findBuildingSiteById(holder, params.getBuildingSiteId().get(0));

        List<BuildingSiteWithCheckDetail> checkDetails = findBuildingSiteCheckDetails(holder, params);

        BuildingSiteWithCheckDetail checkDetail = checkDetails.get(0);

        String name = buildingSite.getBuildingDeveloper().getName() + "的" + buildingSite.getName() + "工程" +
                DateFormatUtils.format(params.getBeginDate(),"MM月dd日") + "至" +
                DateFormatUtils.format(params.getEndDate(),"MM月dd日") + "工资发放详情";

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
        rowTitle.createCell(ATTENCE_DAYS_LOST_COL_IDX).setCellValue("缺卡天数");
        rowTitle.createCell(ATTENCE_DAYS_ABNORMAL_COL_IDX).setCellValue("迟到早退天数");
        rowTitle.createCell(ATTENCE_DAYS_NORMAL_COL_IDX).setCellValue("正常出勤天数");
        rowTitle.createCell(MONEY_COL_IDX).setCellValue("应发工资");

        List<EmployeeWithCheckDetailDto> list = checkDetail.getEmployees();

        for(int i=0; i<list.size(); i++){
            XSSFRow row = sheet.createRow(i+2);
            EmployeeWithCheckDetailDto detail = list.get(i);

            row.createCell(0).setCellValue(i+1);
            row.createCell(NAME_COL_IDX).setCellValue(detail.getEmployee().getName());
            row.createCell(ID_CARD_NO_COL_IDX).setCellValue(detail.getEmployee().getIdCardNo());
            row.createCell(BANK_COL_IDX).setCellValue(detail.getEmployee().getBank());
            row.createCell(BANK_NO_COL_IDX).setCellValue(detail.getEmployee().getBankNo());
            row.createCell(WORK_TYPE_COL_IDX).setCellValue(detail.getEmployee().getWorkType());
            row.createCell(ATTENCE_DAYS_COL_IDX).setCellValue(detail.getCheckRecords().size());
            row.createCell(ATTENCE_DAYS_LOST_COL_IDX).setCellValue(detail.getCheckRecords().values().stream().filter(item->CheckStatusEnum.LOST.equals(item.getCheckStatus())).count());
            row.createCell(ATTENCE_DAYS_ABNORMAL_COL_IDX).setCellValue(detail.getCheckRecords().values().stream().filter(item->CheckStatusEnum.ABNORMAL.equals(item.getCheckStatus())).count());
            row.createCell(ATTENCE_DAYS_NORMAL_COL_IDX).setCellValue(detail.getCheckRecords().values().stream().filter(item->CheckStatusEnum.NORAML.equals(item.getCheckStatus())).count());
        }

        return wb;
    }
}
