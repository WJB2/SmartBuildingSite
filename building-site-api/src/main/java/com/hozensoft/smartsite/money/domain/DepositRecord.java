package com.hozensoft.smartsite.money.domain;

import com.hozensoft.smartsite.enumeration.DepositTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class DepositRecord {

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
     * 款项操作类型，存/退/用
     */
    private DepositTypeEnum depositType;

    /**
     * 银行内部账单流水号
     */
    private String outCredenceId;

    /**
     * 开发商账户号码
     */
    private String bankCardNo;

    /**
     *
     */
    private BigDecimal initDeposit;

    /**
     * 发生金额
     */
    private BigDecimal deposit;

    /**
     * 备注信息
     */
    private String remark;

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
