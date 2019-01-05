package com.hozensoft.smartsite.base.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 工种信息
 */
@Getter
@Setter
public class WorkType {

    /**
     * ID
     */
    private String id;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 所属开发商ID
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
}
