package com.hozensoft.smartsite.base.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingDeveloperValueDto {

    /**
     * ID
     */
    private String id;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 关联组织机构
     */
    private String orgId;

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
     * 当前岗位的ID
     */
    private String currentPositionId;

}
