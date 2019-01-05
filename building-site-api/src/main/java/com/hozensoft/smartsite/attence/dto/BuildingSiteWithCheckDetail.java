package com.hozensoft.smartsite.attence.dto;

import com.hozensoft.smartsite.base.dto.BuildingSiteItemDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BuildingSiteWithCheckDetail {

    private BuildingSiteItemDto buildingSite;

    private List<EmployeeWithCheckDetailDto> employees;
}
