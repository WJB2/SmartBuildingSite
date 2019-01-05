package com.hozensoft.smartsite.attence.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class AttenceRecordValueDto {

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
     * 人员ID
     */
    private String employeeId;


    /**
     * 考勤日期
     */
    private Date checkDate;


    /**
     * 下班打卡时间
     */
    private Date checkOutTime;



    /**
     * 当前岗位ID
     */
    private String currentPositionId;

}
