package com.hozensoft.smartsite.money.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DepositValueDto {

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
     * 存款
     * */
    private BigDecimal deposit;


    /**
     * 当前岗位ID
     */
    private String currentPositionId;
}
