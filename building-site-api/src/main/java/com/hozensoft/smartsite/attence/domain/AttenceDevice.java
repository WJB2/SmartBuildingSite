package com.hozensoft.smartsite.attence.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 考勤设备
 */
@Getter
@Setter
public class AttenceDevice {

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
     * 工地ID
     */
    private String buildingSiteId;

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
