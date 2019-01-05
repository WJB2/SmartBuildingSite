package com.hozensoft.smartsite.money.dto;


import com.hozensoft.global.core.dto.TenantAttrDto;
import com.hozensoft.smartsite.base.dto.BuildingSiteItemDto;
import com.hozensoft.smartsite.enumeration.PaymentStatusEnum;
import com.hozensoft.system.core.dto.OrganizationAttrDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class PaymentDetailItemDto {

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
    private OrganizationAttrDto buildingDeveloper;

    /**
     * 工地ID
     */
    private String buildingSiteId;

    /**
     * 工地
     */
    private BuildingSiteItemDto buildingSite;


    /**
     * 人员姓名
     */
    private String name;

    /**
     * 人员身份证号
     */
    private String idCardNo;

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
     * 银行
     */
    private String bank;

    /**
     * 银行卡号
     */
    private String bankNo;

    /**
     * 工种
     */
    private String workType;

    /**
     * 考勤天数
     */
    private BigDecimal attenceDays;

    /**
     * 工资总额
     */
    private BigDecimal money;

    /**
     * 发放状态
     */
    private PaymentStatusEnum status;


    /**
     * 标记
     */
    private String remake;


    private Boolean deletedFlag;

}
