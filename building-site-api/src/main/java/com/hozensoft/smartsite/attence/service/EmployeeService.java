package com.hozensoft.smartsite.attence.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.hozensoft.smartsite.attence.domain.Employee;
import com.hozensoft.smartsite.attence.dto.*;
import com.hozensoft.smartsite.base.dto.EmployeeWechatReportDto;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.struct.pagehelper.Pageable;
import com.hozensoft.system.utils.bean.ContextHolder;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * 工人信息服务类
 */
public interface EmployeeService {

    /**
     * 新增工人信息
     *
     * @param buildingDeveloper
     */
    EmployeeDto addEmployee(ContextHolder holder, EmployeeValueDto buildingDeveloper);

    /**
     * 编辑工人信息信息
     *
     * @param buildingDeveloper
     */
    EmployeeDto editEmployee(ContextHolder holder,EmployeeValueDto buildingDeveloper);

    /**
     * 根据ID删除工人信息信息
     * @param buildingDeveloperId
     */
    void deleteEmployeeById(ContextHolder holder,String buildingDeveloperId);

    /**
     * 根据ID加载完整的工人信息信息
     *
     * @param buildingDeveloperId
     * @return
     */
    Employee loadEmployeeById(ContextHolder holder,String buildingDeveloperId);

    /**
     * 根据员工编码查找员工
     *
     * @param holder
     * @param buildingDeveloperId
     * @param buildingSiteId
     * @param employeeCode
     * @return
     */
    Employee loadEmployeeByCode(ContextHolder holder,String buildingDeveloperId, String buildingSiteId, String employeeCode);

    /**
     *
     * @param holder
     * @param employeeId
     * @return
     */
    EmployeeDto findEmployeeById(ContextHolder holder,String employeeId);

    /**
     * 根据工号查找
     *
     * @param holder
     * @param employeeCode
     * @return
     */
    EmployeeDto findEmployeeByCode(ContextHolder holder,String buildingDeveloperId,  String employeeCode);

    /**
     * 查询工人信息列表信息
     *
     * @param params
     * @param limitable
     * @return
     */
    List<EmployeeItemDto> findEmployeeList(ContextHolder holder,EmployeeQueryDto params, Limitable limitable);

    /**
     * 查询工人信息分页列表信息
     *
     * @param params
     * @param pageable
     * @return
     */
    PageInfo<EmployeeItemDto> findEmployeePage(ContextHolder holder,EmployeeQueryDto params, Pageable pageable);

    EmployeeWechatReportDto reportEmployeeAttenceRecordAndSalary(ContextHolder holder, String employeeId, Date beginDate, Date endDate);

    void  importEmployee(ContextHolder holder, EmployeeValueDto employeeDto,MultipartFile detailExcelFile);
}
