package com.hozensoft.smartsite.base.dto;

import com.hozensoft.system.core.dto.OrganizationAttrDto;
import com.hozensoft.system.core.dto.StaffAttrDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingSiteItemDto {

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
     * 开发商信息
     */
    private OrganizationAttrDto buildingDeveloper;

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
     * 负责人
     */
    private StaffAttrDto adminStaff;

    /**
     * 逻辑删除标记位
     */
    private Boolean deletedFlag;
}
