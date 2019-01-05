package com.hozensoft.smartsite.attence.dto;

import com.hozensoft.global.core.dto.TenantAttrDto;


import com.hozensoft.smartsite.base.dto.BuildingDeveloperDto;
import com.hozensoft.smartsite.base.dto.BuildingSiteDto;
import com.hozensoft.system.core.dto.OrganizationAttrDto;
import com.hozensoft.system.core.dto.StaffAttrDto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 考勤设备
 */
@Getter
@Setter
public class AttenceDeviceDto implements Serializable {

    /**
     * ID
     */
    private String id;

    /**
     * 租户ID
     */
    private String tenantId;

    private TenantAttrDto tenant;

    /**
     * 开发商ID
     */
    private String buildingDeveloperId;

    private BuildingDeveloperDto buildingDeveloper;

    /**
     * 工地ID
     */
    private String buildingSiteId;

    private BuildingSiteDto buildingSite;

    /**
     * 设备唯一编码
     */
    private String sn;

    /**
     * 设备类型
     */
    private String type;

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
