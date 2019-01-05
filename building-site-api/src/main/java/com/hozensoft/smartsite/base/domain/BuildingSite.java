package com.hozensoft.smartsite.base.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 工地信息
 */
@Getter
@Setter
public class BuildingSite {

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
     * 工地名称
     */
    private String name;

    /**
     * 工地名称简拼
     */
    private String namePinyin;

    /**
     * 工地地址
     */
    private String address;

    /**
     * 负责人ID
     */
    private String adminStaffId;

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
