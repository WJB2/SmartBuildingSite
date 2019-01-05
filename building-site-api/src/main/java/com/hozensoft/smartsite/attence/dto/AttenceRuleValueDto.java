package com.hozensoft.smartsite.attence.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttenceRuleValueDto {

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

    private Boolean breakEnabled;

    /**
     * 考勤开始时间
     */
    private String beginTime;

    private String breakBeginTime;

    private String breakEndTime;

    /**
     * 考勤结束时间
     */
    private String endTime;

}
