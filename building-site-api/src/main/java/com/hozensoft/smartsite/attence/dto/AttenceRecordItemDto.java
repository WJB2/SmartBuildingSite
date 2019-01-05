package com.hozensoft.smartsite.attence.dto;

import com.hozensoft.smartsite.enumeration.CheckStatusEnum;
import com.hozensoft.smartsite.enumeration.ClockTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class AttenceRecordItemDto {

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
     * 工种
     */
    private String workType;

    /**
     * 考勤日期
     */
    private Date checkDate;


    private Boolean breakEnabled;

    /**
     * 上班考勤状态
     */
    private CheckStatusEnum checkInStatus;

    /**
     * 打卡类型
     */
    private ClockTypeEnum clockInType;

    /**
     * 上班打卡时间
     */
    private Date checkInTime;

    /**
     * 休息退卡状态
     */
    private CheckStatusEnum breakCheckOutStatus;

    /**
     * 休息退卡类型
     */
    private ClockTypeEnum breakClockOutType;

    /**
     * 休息退卡时间
     */
    private Date breakCheckOutTime;

    /**
     * 休息打卡状态
     */
    private CheckStatusEnum breakCheckInStatus;

    /**
     * 休息打卡类型
     */
    private ClockTypeEnum breakClockInType;

    /**
     * 休息打卡时间
     */
    private Date breakCheckInTime;

    /**
     * 下班考勤状态
     */
    private CheckStatusEnum checkOutStatus;

    /**
     * 退卡类型
     */
    private ClockTypeEnum clockOutType;

    /**
     * 下班打卡时间
     */
    private Date checkOutTime;

    /**
     * 今日考勤状态
     */
    private CheckStatusEnum checkStatus;

    /**
     * 时间工作日数量
     */
    private BigDecimal workingDay;

    public Boolean getBreakEnabled(){
        return this.breakEnabled!=null?this.breakEnabled:false;
    }

    public CheckStatusEnum getCheckStatus(){
        if(CheckStatusEnum.LOST.equals(getCheckInStatus()) ||
                CheckStatusEnum.LOST.equals(getCheckOutStatus()) ||
                (getBreakEnabled() && (CheckStatusEnum.LOST.equals(getBreakCheckInStatus())||
                        CheckStatusEnum.LOST.equals(getBreakCheckOutStatus())))){
            return CheckStatusEnum.LOST;
        }

        if(CheckStatusEnum.ABNORMAL.equals(getCheckInStatus()) ||
                CheckStatusEnum.ABNORMAL.equals(getCheckOutStatus()) ||
                (getBreakEnabled() && (CheckStatusEnum.ABNORMAL.equals(getBreakCheckInStatus())||
                        CheckStatusEnum.ABNORMAL.equals(getBreakCheckOutStatus())))){
            return CheckStatusEnum.ABNORMAL;
        }

        return CheckStatusEnum.NORAML;
    }
}
