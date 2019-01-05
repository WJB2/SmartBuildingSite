package com.hozensoft.smartsite.money.dto;

import com.hozensoft.global.core.dto.TenantAttrDto;
import com.hozensoft.smartsite.base.dto.BuildingDeveloperDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class DepositQueryDto {

    /**
     * ID
     */
    private List<String> id;

    /**
     * 租户ID
     */
    private String tenantId;



    /**
     * 开发商ID
     */
    private List<String> buildingDeveloperId;



    /**
     * 存款
     * */
    private BigDecimal deposit;
}
