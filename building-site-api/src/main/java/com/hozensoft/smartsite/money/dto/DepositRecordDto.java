package com.hozensoft.smartsite.money.dto;

import com.hozensoft.global.core.dto.TenantAttrDto;
import com.hozensoft.smartsite.enumeration.DepositTypeEnum;
import com.hozensoft.system.core.dto.OrganizationAttrDto;
import com.hozensoft.system.core.dto.StaffAttrDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
    /**
     * 存款记录
     * */
public class DepositRecordDto {

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
     * */
    private TenantAttrDto tenant;

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
     * 发生金额
     */
    private BigDecimal deposit;

    /**
     * 备注信息
     */
    private String remark;


    private Boolean deletedFlag;

    private String createdById;

    private StaffAttrDto createdBy;

    private String createdOrgId;

    private OrganizationAttrDto createdOrg;

    private Date createdTime;

    private String updatedById;

    private StaffAttrDto updatedBy;

    private String updatedOrgId;

    private OrganizationAttrDto updatedOrg;

    private Date updatedTime;

    private String deletedById;

    private StaffAttrDto deletedBy;

    private String deletedOrgId;

    private OrganizationAttrDto deletedOrg;

    private Date deletedTime;

}
