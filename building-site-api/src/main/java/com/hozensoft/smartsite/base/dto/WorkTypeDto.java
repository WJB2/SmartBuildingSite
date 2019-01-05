package com.hozensoft.smartsite.base.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkTypeDto {

    /**
     * ID
     */
    private String id;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 所属开发商
     */
    private String buildingDeveloperId;

    /**
     * 关联工地ID
     */
    private String buildingSiteId;

    /**
     * 工种名称
     */
    private String name;

    /**
     * 工种名称简拼
     */
    private String namePinyin;

    /**
     * 显示顺序
     */
    private Integer sortNo;

    /**
     * 当前岗位ID
     */
    private String currentPositionId;

}
