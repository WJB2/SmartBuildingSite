package com.hozensoft.smartsite.attence.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 设备移机记录
 */
@Getter
@Setter
public class AttenceDeviceTransferValueDto {

    /**
     * ID
     */
    private String id;

    /**
     * 考勤设备ID
     */
    private String attenceDeviceId;

    /**
     * 原所属开发商
     */
    private String originalBuildingDeveloperId;

    /**
     * 原所属工地
     */
    private String originalBuildingSiteId;

    /**
     * 目标开发商
     */
    private String targetBuildingDeveloperId;

    /**
     * 目标工地
     */
    private String targetBuildingSiteId;

    /**
     * 创建人
     */
    private String createdById;

    /**
     * 创建组织机构
     */
    private String createdOrgId;

    /**
     * 创建时间
     */
    private Date createdTime;


    /**
     * 当前岗位ID
     */
    private String currentPositionId;

}
