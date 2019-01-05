package com.hozensoft.smartsite.money.dto;


import com.hozensoft.global.core.dto.TenantAttrDto;
import com.hozensoft.smartsite.base.dto.BuildingDeveloperDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter

/**
 * 工资发放记录
 */
public class PaymentItemDto {

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
    private BuildingDeveloperDto buildingDeveloper;



    /**
     * 工资所属年月份
     */
    private Integer yearMonth;


    /**
     * 所属周期开始日期
     */
    private Date beginDate;


    /**
     * 所属周期结束日期
     */
    private Date endDate;


    /**
     * 工资总额
     */
    private BigDecimal money;


    private Boolean deletedFlag;
}
