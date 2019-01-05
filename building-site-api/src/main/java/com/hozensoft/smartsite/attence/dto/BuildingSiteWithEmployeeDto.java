package com.hozensoft.smartsite.attence.dto;

import com.hozensoft.smartsite.attence.domain.Employee;
import com.hozensoft.smartsite.base.domain.BuildingSite;
import com.hozensoft.smartsite.base.dto.BuildingSiteItemDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BuildingSiteWithEmployeeDto {

    private AttenceRuleItemDto rule;

    private BuildingSiteItemDto buildingSite;

    private List<EmployeeItemDto> employees;
}
