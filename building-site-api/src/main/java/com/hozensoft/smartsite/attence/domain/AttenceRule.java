package com.hozensoft.smartsite.attence.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 考勤规则
 */
@Getter
@Setter
public class AttenceRule {

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
     * 开启休息标记
     */
    private Boolean breakEnabled;

    /**
     * 考勤开始时间
     */
    private String beginTime;

    /**
     * 中间休息开始时间
     */
    private String breakBeginTime;

    /**
     * 中间休息结束时间
     */
    private String breakEndTime;

    /**
     * 考勤结束时间
     */
    private String endTime;

    public Boolean getBreakEnabled(){
        return this.breakEnabled!=null?this.breakEnabled:false;
    }


    public static AttenceRule buildDefault(String tenantId, String buildingDeveloperId, String buildingSiteId){
        AttenceRule rule = new AttenceRule();
        rule.setBeginTime("08:00");
        rule.setEndTime("17:30");
        rule.setBuildingDeveloperId(buildingDeveloperId);
        rule.setBuildingSiteId(buildingSiteId);
        rule.setTenantId(tenantId);

        return rule;
    }
}
