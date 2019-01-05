package com.hozensoft.smartsite.money.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class PaymentDetailValueDto {

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
     * 工地ID
     */
    private String buildingSiteId;


    /**
     * 人员ID
     */
    private String employeeId;


    /**
     * 工资所属年月份
     */
    private Integer yearMonth;


    /**
     * 工资周期起始日期
     */
    private Date beginDate;

    /**
     * 工资周期结束日期
     */
    private Date endDate;

    /**
     * 工资总额
     */
    private BigDecimal money;


    /**
     * 当前岗位ID
     */
    private String currentPositionId;


    /**
     * 标记
     */
    private String remake;

    private Boolean deletedFlag;
}
