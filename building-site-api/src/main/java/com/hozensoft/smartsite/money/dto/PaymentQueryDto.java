package com.hozensoft.smartsite.money.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class PaymentQueryDto {

    /**
     * ID
     */
    private String id;



    /**
     * 租户ID
     */
    private String tenantId;


    /**
     * 开发商ID
     */
    private String buildingDeveloperId;


    /**
     * 工资所属年月份
     */
    private Integer yearMonth;


    /**
     * 工资总额
     */
    private BigDecimal money;
}
