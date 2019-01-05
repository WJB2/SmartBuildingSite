package com.hozensoft.smartsite.attence.dto;

import com.hozensoft.smartsite.base.dto.BuildingSiteDto;
import com.hozensoft.smartsite.base.dto.BuildingSiteItemDto;
import com.hozensoft.system.core.dto.OrganizationAttrDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttenceRuleItemDto {

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
     * 开发商
     */
    private OrganizationAttrDto buildingDevelper;

    /**
     * 工地ID
     */
    private String buildingSiteId;

    /**
     * 工地
     */
    private BuildingSiteItemDto buildingSiteDto;

    private Boolean breakEnabled;

    /**
     * 考勤开始时间
     */
    private String beginTime;

    private String breakEndTime;

    private String breakBeginTime;

    /**
     * 考勤结束时间
     */
    private String endTime;
    
}
