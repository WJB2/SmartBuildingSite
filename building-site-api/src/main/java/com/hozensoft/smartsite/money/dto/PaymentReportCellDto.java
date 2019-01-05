package com.hozensoft.smartsite.money.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentReportCellDto {

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 开发商ID
     */
    private String buildingDeveloperId;

    /**
     * 工地ID
     */
    private String buildingSiteId;

    /**
     * 工资所属年月
     */
    private Integer yearMonth;

    /**
     * 应发工资
     */
    private BigDecimal grossSalary;

    /**
     * 已发工资
     */
    private BigDecimal paidSalary;
}
