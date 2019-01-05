package com.hozensoft.smartsite.base.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BuildingDeveloperQueryDto {

    /**
     * ID
     */
    private List<String> id;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 统一社会信用代码
     */
    private String creditCode;

    /**
     * 开发商名称
     */
    private String nameLike;

    /**
     * 开发商法人
     */
    private String corporation;

    /**
     * 开发商法人联系电话
     */
    private String corporationMobile;

    /**
     * 开发商联系电话
     */
    private String telephone;

    /**
     * 开发商传真号码
     */
    private String fax;

    /**
     * 开发商银行账户号码
     */
    private String bankNo;

    /**
     * 开发商模糊匹配文本
     */
    private String buildingDeveloperText;

    private Boolean deletedFlag;

    private List<String> authorizedOrgId;
}
