package com.hozensoft.smartsite.attence.service;

import com.github.pagehelper.PageInfo;
import com.hozensoft.smartsite.attence.domain.AttenceRecord;
import com.hozensoft.smartsite.attence.dto.*;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.struct.pagehelper.Pageable;
import com.hozensoft.system.utils.bean.ContextHolder;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

/**
 * 员工日考勤记录服务类
 */


public interface AttenceRecordService {


    void clocking(ContextHolder holder,String positionId, EmployeeItemDto employee);

    void repairClocking(ContextHolder holder, AttenceRecordRepairDto repairDto);

    List<BuildingSiteWithEmployeeDto> findEmployeeForManualClocking(ContextHolder holder,String buildingDeveloperId, String staffId);

    List<BuildingSiteWithCheckDetail> findBuildingSiteCheckDetails(ContextHolder holder,AttenceRecordQueryDto params);

    XSSFWorkbook generateSalaryReportExcel(ContextHolder holder,AttenceRecordQueryDto params);
}
