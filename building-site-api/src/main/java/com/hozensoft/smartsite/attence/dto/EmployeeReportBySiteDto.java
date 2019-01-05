package com.hozensoft.smartsite.attence.dto;

import com.hozensoft.smartsite.base.dto.BuildingSiteDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class EmployeeReportBySiteDto {

    private BuildingSiteDto buildingSite;

    private List<AttenceRecordItemDto> attenceRecords;

    private BigDecimal salary;
}
