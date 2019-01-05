package com.hozensoft.smartsite.attence.dto;

import com.hozensoft.global.core.dto.TenantAttrDto;
import com.hozensoft.smartsite.base.dto.BuildingDeveloperItemDto;
import com.hozensoft.smartsite.base.dto.BuildingSiteItemDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AttenceDeviceTransferDto {

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
     * 考勤设备ID
     */
    private String attenceDeviceId;

    /**
     * 考勤设备信息
     */
    private AttenceDeviceItemDto attenceDevice;

    /**
     * 原所属开发商ID
     */
    private String originalBuildingDeveloperId;

    /**
     * 原所属开发商
     */
    private BuildingDeveloperItemDto originalBuildingDeveloper;

    /**
     * 原所属工地ID
     */
    private String originalBuildingSiteId;

    /**
     * 原所属工地
     */
    private BuildingSiteItemDto originalBuildingSite;

    /**
     * 目标开发商ID
     */
    private String targetBuildingDeveloperId;

    /**
     * 目标开发商
     */
    private BuildingDeveloperItemDto targetBuildingDeveloper;

    /**
     * 目标工地ID
     */
    private String targetBuildingSiteId;

    /**
     * 目标工地
     */
    private String targetBuildingSite;

    /**
     * 创建人ID
     */
    private String createdById;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建组织机构ID
     */
    private String createdOrgId;

    /**
     * 创建组织机构
     */
    private String createOrg;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 当前岗位ID
     */
    private String currentPositionId;

}
