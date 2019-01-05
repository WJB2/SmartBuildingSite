package com.hozensoft.smartsite.base.dto;

import com.hozensoft.global.core.dto.TenantAttrDto;
import com.hozensoft.system.core.dto.OrganizationAttrDto;
import com.hozensoft.system.core.dto.StaffAttrDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BuildingDeveloperDto {

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
     * 对应组织机构ID
     */
    private String orgId;

    /**
     * 对应组织机构
     */
    private OrganizationAttrDto org;

    /**
     * 统一社会信用代码
     */
    private String creditCode;

    /**
     * 开发商名称
     */
    private String name;

    /**
     * 开发商名称简拼
     */
    private String namePinyin;

    /**
     * 开发商法人
     */
    private String corporation;

    /**
     * 开发商法人联系电话
     */
    private String corporationMobile;

    /**
     * 开发管理员用户ID
     */
    private String adminStaffId;

    /**
     * 开发商联系电话
     */
    private String telephone;

    /**
     * 开发商传真号码
     */
    private String fax;

    /**
     * 开发商联系地址
     */
    private String address;

    /**
     * 开发商银行账户号码
     */
    private String bankNo;

    /**
     * 逻辑删除标记位
     */
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
