package com.hozensoft.smartsite.base.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingSiteValueDto {

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

    /**
     * 当前岗位ID
     */
    private String currentPositionId;

}
