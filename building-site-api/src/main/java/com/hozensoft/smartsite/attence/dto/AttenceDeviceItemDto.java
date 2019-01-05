package com.hozensoft.smartsite.attence.dto;

import com.hozensoft.global.core.dto.TenantAttrDto;
import com.hozensoft.smartsite.base.domain.BuildingSite;
import com.hozensoft.smartsite.base.dto.BuildingDeveloperDto;
import com.hozensoft.smartsite.base.dto.BuildingDeveloperItemDto;
import com.hozensoft.smartsite.base.dto.BuildingSiteItemDto;
import com.hozensoft.system.core.dto.OrganizationAttrDto;
import com.hozensoft.system.core.dto.StaffAttrDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 考勤设备
 */
@Getter
@Setter
public class AttenceDeviceItemDto {

    /**
     * ID
     */
    private String id;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 租户信息
     */
    private TenantAttrDto tenant;

    /**
     * 开发商ID
     */
    private String buildingDeveloperId;

    /**
     * 开发商
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
     * 设备唯一编码
     */
    private String sn;

    /**
     * 设备类型
     */
    private String type;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 逻辑删除标记
     */
    private Boolean deletedFlag;

    /**
     * 在线标记
     */
    private Boolean onlineFlag;

}
