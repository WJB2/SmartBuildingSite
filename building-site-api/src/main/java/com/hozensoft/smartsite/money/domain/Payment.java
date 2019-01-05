package com.hozensoft.smartsite.money.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 工资发放记录
 */
@Getter
@Setter
public class Payment {

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
     * 所属工地ID
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

    private Boolean deletedFlag;

    private String createdById;

    private String createdOrgId;

    private Date createdTime;

    private String updatedById;

    private String updatedOrgId;

    private Date updatedTime;

    private String deletedById;

    private String deletedOrgId;

    private Date deletedTime;
}
