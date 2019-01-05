package com.hozensoft.smartsite.base.dto;

import com.hozensoft.smartsite.attence.dto.EmployeeDto;
import com.hozensoft.smartsite.attence.dto.EmployeeReportBySiteDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmployeeWechatReportDto {

    private EmployeeDto employee;

    private List<EmployeeReportBySiteDto> reports;
}
