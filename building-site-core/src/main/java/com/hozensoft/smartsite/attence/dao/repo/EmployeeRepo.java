package com.hozensoft.smartsite.attence.dao.repo;

import com.hozensoft.smartsite.attence.domain.Employee;
import org.apache.ibatis.annotations.Param;

public interface EmployeeRepo {

    int addEmployee(Employee employee);

    int editEmployee(Employee employee);

    int patchEditEmployee(Employee employee);

    int deleteEmployeeById(@Param("tenantId")String tenantId, @Param("employeeId") String employeeId);

    Employee loadEmployeeById(@Param("tenantId")String tenantId, @Param("employeeId") String employeeId);

    Employee loadEmployeeByCode(@Param("tenantId")String tenantId,
                              @Param("buildingDeveloperId") String buildingDeveloperId,
                              @Param("buildingSiteId") String buildingSiteId,
                              @Param("employeeCode") String employeeCode);
}
