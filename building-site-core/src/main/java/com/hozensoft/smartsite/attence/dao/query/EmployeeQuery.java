package com.hozensoft.smartsite.attence.dao.query;

import com.hozensoft.smartsite.attence.dto.EmployeeDto;
import com.hozensoft.smartsite.attence.dto.EmployeeItemDto;
import com.hozensoft.smartsite.attence.dto.EmployeeQueryDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeQuery {

    EmployeeDto findEmployeeById(@Param("employeeId") String employeeId);

    EmployeeDto findEmployeeByCode(@Param("buildingDeveloperId")String buildingDeveloperId,
                                   @Param("employeeCode")String employeeCode);

    List<EmployeeItemDto> findEmployeeList(EmployeeQueryDto params);
}
