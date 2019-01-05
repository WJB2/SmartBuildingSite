package com.hozensoft.smartsite.attence.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
public class EmployeeWithCheckDetailDto implements Serializable {

    private EmployeeItemDto employee;

    private Map<String, AttenceRecordItemDto> checkRecords;
}
