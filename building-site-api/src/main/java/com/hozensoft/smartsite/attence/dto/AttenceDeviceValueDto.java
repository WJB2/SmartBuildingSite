package com.hozensoft.smartsite.attence.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 考勤设备
 */
@Getter
@Setter
public class AttenceDeviceValueDto {

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

    /**
     * 当前岗位ID
     */
    private String currentPositionId;

}
