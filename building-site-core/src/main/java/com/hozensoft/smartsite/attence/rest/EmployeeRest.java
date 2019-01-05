package com.hozensoft.smartsite.attence.rest;

import com.github.pagehelper.PageInfo;
import com.hozensoft.config.rest.converter.DateConverter;
import com.hozensoft.smartsite.attence.domain.Employee;
import com.hozensoft.smartsite.attence.dto.*;
import com.hozensoft.smartsite.attence.service.EmployeeService;
import com.hozensoft.smartsite.base.dto.EmployeeWechatReportDto;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.struct.pagehelper.Pageable;
import com.hozensoft.system.utils.ContextUtils;
import com.hozensoft.system.utils.bean.ContextHolder;
import io.swagger.annotations.Api;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Api(value="开发商管理接口")
@RestController
@RequestMapping("/api/building-site/employee")
public class EmployeeRest {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public @ResponseBody EmployeeDto addEmployee(@RequestBody EmployeeValueDto employeeDto){

        employeeDto.setCurrentPositionId(ContextUtils.getCurrentPosition().getId());

        return employeeService.addEmployee(ContextUtils.fetchContext(), employeeDto);
    }

    @PostMapping("/excel")
    public @ResponseBody void importEmployee(@RequestParam(value="buildingSiteId", required = false) String buildingSiteId,
                                             @RequestParam(value="file", required = false)MultipartFile file){
        EmployeeValueDto employeeDto = new EmployeeValueDto();
        employeeDto.setBuildingSiteId(buildingSiteId);
        employeeDto.setCurrentPositionId(ContextUtils.getCurrentPosition().getId());

        employeeService.importEmployee(ContextUtils.fetchContext(),employeeDto,file);

    }

    @PutMapping("/{employeeId}")
    public @ResponseBody EmployeeDto editEmployee(@PathVariable("employeeId")String employeeId,
                                                                    @RequestBody EmployeeValueDto employeeDto){

        employeeDto.setCurrentPositionId(ContextUtils.getCurrentPosition().getId());

        return employeeService.editEmployee(ContextUtils.fetchContext(), employeeDto);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployeeById(@PathVariable("employeeId")String employeeId){

        employeeService.deleteEmployeeById(ContextUtils.fetchContext(), employeeId);
    }

    @GetMapping("/{employeeId}")
    public @ResponseBody EmployeeDto findEmployeeById(@PathVariable("employeeId")String employeeId){

        return employeeService.findEmployeeById(ContextUtils.fetchContext(), employeeId);
    }

    @GetMapping("/report/employee-attence-record-and-salary")
    public @ResponseBody
    EmployeeWechatReportDto reportEmployeeAttenceRecordAndSalary(
                                                                       @RequestParam("employeeId") String employeeId,
                                                                       @RequestParam("beginDate") Date beginDate,
                                                                       @RequestParam("endDate") Date endDate){

        return employeeService.reportEmployeeAttenceRecordAndSalary(ContextUtils.fetchContext(), employeeId, beginDate, endDate);
    }

    @GetMapping("/list")
    public @ResponseBody
    List<EmployeeItemDto> findEmployeeList(EmployeeQueryDto params, Limitable limitable){
        if(StringUtils.isBlank(params.getTenantId())){
            params.setTenantId(ContextUtils.fetchContext().getTenantId());
        }

        if(CollectionUtils.isEmpty(params.getBuildingDeveloperId())){
            params.setBuildingDeveloperId(Arrays.asList(ContextUtils.getCurrentOrganization().getId()));
        }

        return employeeService.findEmployeeList(ContextUtils.fetchContext(), params, limitable);
    }

    @GetMapping("/page")
    public @ResponseBody
    PageInfo<EmployeeItemDto> findEmployeePage(EmployeeQueryDto params, Pageable pageable){
        if(StringUtils.isBlank(params.getTenantId())){
            params.setTenantId(ContextUtils.fetchContext().getTenantId());
        }

        if(CollectionUtils.isEmpty(params.getBuildingDeveloperId())){
            params.setBuildingDeveloperId(Arrays.asList(ContextUtils.getCurrentOrganization().getId()));
        }

        return employeeService.findEmployeePage(ContextUtils.fetchContext(), params, pageable);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateConverter());
    }
}
