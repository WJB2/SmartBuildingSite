package com.hozensoft.smartsite.money.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class PaymentValueDto {


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



    /**
     * 当前岗位ID
     */
    private String currentPositionId;
}
