package com.hozensoft.smartsite.attence.transfermer;

import com.hozensoft.smartsite.attence.domain.Employee;
import com.hozensoft.smartsite.attence.dto.EmployeeValueDto;
import com.hozensoft.utils.persistent.IdGen;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.Date;

public class EmployeeTransformer {
    public static Employee transformEmployeeAddDtoToDomain(EmployeeValueDto employeeDto) {

        Employee employee = new Employee();

        BeanUtils.copyProperties(employeeDto, employee);

        employee.setUnregistered(false);
        employee.setRegisterTime(new Date());
        employee.setFingerprintRegistered(false);
        employee.setFaceRegistered(false);

        if(StringUtils.isBlank(employee.getId())){
            employee.setId(IdGen.generate());
        }

        if(employee.getDeletedFlag()==null){
            employee.setDeletedFlag(false);
        }

        return employee;
    }

    public static Employee transformEmployeeEditDtoToDomain(EmployeeValueDto employee) {

        Employee domain = new Employee();

        BeanUtils.copyProperties(employee, domain);

        return domain;
    }

    public static Employee transformEmployeeEditDtoToDomain(EmployeeValueDto employee, Employee domain) {

        BeanUtils.copyProperties(employee, domain);

        return domain;
    }

}