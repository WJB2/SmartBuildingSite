package com.hozensoft.smartsite.money.dto;

import com.hozensoft.smartsite.enumeration.DepositTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DepositRecordValueDto {


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
     * 金额
     */
    private BigDecimal money;

    /**
     * 备注信息
     */
    private String remark;


    /**
     * 当前岗位ID
     */
    private String currentPositionId;

}
