package com.hozensoft.smartsite.money.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class PaymentDetailQueryDto {

    /**
     * ID
     */
    private List<String> id;

    /**
     * 租户ID
     */
    private String tenantId;


    /**
     * 开发商ID
     */
    private List<String> buildingDeveloperId;


    /**
     * 工地ID
     */
    private List<String> buildingSiteId;


    /**
     * 人员ID
     */
    private String employeeId;

    /**
     * 工资所属年月份
     */
    private Integer yearMonth;

    /**
     * 工资总额
     */
    private BigDecimal money;


    /**
     * 标记
     */
    private String remake;

    private Boolean deletedFlag;

}
