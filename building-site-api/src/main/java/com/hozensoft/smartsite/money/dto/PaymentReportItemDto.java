package com.hozensoft.smartsite.money.dto;

import com.hozensoft.smartsite.base.dto.BuildingDeveloperItemDto;
import com.hozensoft.smartsite.base.dto.BuildingSiteItemDto;
import com.hozensoft.system.core.dto.OrganizationAttrDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class PaymentReportItemDto {

    private OrganizationAttrDto buildingDeveloper;

    private BuildingSiteItemDto buildingSite;

    private Map<Integer, PaymentReportCellDto> monthReports;
}
