package com.hozensoft.smartsite.money.dto;

import com.hozensoft.global.core.dto.TenantAttrDto;
import com.hozensoft.smartsite.base.dto.BuildingDeveloperDto;
import com.hozensoft.smartsite.base.dto.BuildingDeveloperItemDto;
import com.hozensoft.system.core.dto.OrganizationAttrDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DepositItemDto {

    /**
     * ID
     */
    private String id;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 租户
     */
    private TenantAttrDto tenant;

    /**
     * 开发商ID
     */
    private String buildingDeveloperId;

    /**
     * 开发商信息
     */
    private BuildingDeveloperItemDto buildingDeveloper;

    /**
     * 存款
     * */
    private BigDecimal deposit;


    private Boolean deletedFlag;

}
