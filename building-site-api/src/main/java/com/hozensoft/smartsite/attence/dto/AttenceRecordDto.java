package com.hozensoft.smartsite.attence.dto;


import com.hozensoft.smartsite.enumeration.ClockTypeEnum;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import com.hozensoft.smartsite.enumeration.CheckStatusEnum;


/**
 * 员工日考勤记录
 */

@Getter
@Setter
public class AttenceRecordDto {


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
     * 正常上班时间
     */
    private Date beginTime;

    /**
     * 正常下班时间
     */
    private Date endTime;

    /**
     * 考勤日期
     */
    private Date checkDate;

    /**
     * 上班考勤状态
     */
    private CheckStatusEnum checkInStatus;

    /**
     * 上报考勤打卡类型
     */
    private ClockTypeEnum checkInType;

    /**
     * 上班打卡时间
     */
    private Date checkInTime;

    /**
     * 下班考勤状态
     */
    private CheckStatusEnum checkOutStatus;

    /**
     * 下班考勤打卡类型
     */
    private ClockTypeEnum checkOutType;

    /**
     * 下班打卡时间
     */
    private Date checkOutTime;

    private CheckStatusEnum checkStatus;

    public  CheckStatusEnum getCheckInStatus(){
        if(checkInTime==null){
            return CheckStatusEnum.LOST;
        }

        if(checkInTime.after(beginTime)){
            return CheckStatusEnum.ABNORMAL;
        }

        return CheckStatusEnum.NORAML;
    }

    public  CheckStatusEnum getCheckOutStatus(){
        if(checkOutTime==null){
            return CheckStatusEnum.LOST;
        }

        if(checkOutTime.before(endTime)){
            return CheckStatusEnum.ABNORMAL;
        }

        return CheckStatusEnum.NORAML;
    }

    public CheckStatusEnum getCheckStatus(){
        if(CheckStatusEnum.LOST.equals(getCheckInStatus()) ||
                CheckStatusEnum.LOST.equals(getCheckOutStatus())){
            return CheckStatusEnum.LOST;
        }

        if(CheckStatusEnum.ABNORMAL.equals(getCheckInStatus()) ||
                CheckStatusEnum.ABNORMAL.equals(getCheckOutStatus())){
            return CheckStatusEnum.ABNORMAL;
        }

        return CheckStatusEnum.NORAML;
    }

}
