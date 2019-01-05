package com.hozensoft.smartsite.attence.domain;

import com.hozensoft.smartsite.attence.dto.EmployeeItemDto;
import com.hozensoft.smartsite.enumeration.CheckStatusEnum;
import com.hozensoft.smartsite.enumeration.CheckTypeEnum;
import com.hozensoft.smartsite.enumeration.ClockTypeEnum;
import com.hozensoft.smartsite.iclock.dto.IClockRecordDto;
import com.hozensoft.utils.date.DateParseUtils;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 员工日考勤记录
 */
@Getter
@Setter
public class AttenceRecord {

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
     * 开启休息配置
     */
    private Boolean breakEnabled;

    /**
     * 开始时间
     */
    private Date beginTime;

    /**
     * 休息开始时间
     */
    private Date breakBeginTime;

    /**
     * 休息结束时间
     */
    private Date breakEndTime;

    /**
     * 结束时间
     */
    private Date endTime;

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
     * 下班打卡类型
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

    /**
     * 更新人ID
     */
    private String updatedById;

    /**
     * 更新部门ID
     */
    private String updatedOrgId;

    /**
     * 更新时间
     */
    private Date updatedTime;

    public  CheckStatusEnum getCheckInStatus(){
        if(checkInTime==null){
            return CheckStatusEnum.LOST;
        }

        if(checkInTime.after(beginTime)){
            return CheckStatusEnum.ABNORMAL;
        }

        return CheckStatusEnum.NORAML;
    }

    public CheckStatusEnum getBreakCheckOutStatus(){

        if(breakCheckOutTime==null){
            return CheckStatusEnum.LOST;
        }

        if(breakCheckOutTime.before(breakBeginTime)){
            return CheckStatusEnum.ABNORMAL;
        }

        return CheckStatusEnum.NORAML;
    }

    public CheckStatusEnum getBreakCheckInStatus(){

        if(breakCheckInTime==null){
            return CheckStatusEnum.LOST;
        }

        if(breakCheckInTime.after(breakEndTime)){
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

    public Boolean getBreakEnabled(){
        return this.breakEnabled!=null?this.breakEnabled:false;
    }



    public void clock(IClockRecordDto item){
        AttenceRecord record = this;
        if(!item.getClockTime().after(record.getBeginTime()) && record.getBeginTime()==null){
            record.setCheckInTime(item.getClockTime());
            record.setClockInType(item.getType());
        }

        if(record.getBreakEnabled()!=null && record.getBreakEnabled()){
            if(item.getClockTime().after(record.getBeginTime()) && !item.getClockTime().after(record.getBreakBeginTime())){
                if(record.getCheckInTime()==null){
                    record.setCheckInTime(item.getClockTime());
                    record.setClockInType(item.getType());
                }else{
                    record.setBreakCheckOutTime(item.getClockTime());
                    record.setBreakClockOutType(item.getType());
                }
            }

            if(item.getClockTime().after(record.getBreakBeginTime()) && !item.getClockTime().after(record.getBreakEndTime())){
                if(record.getCheckInTime()!=null){
                    if(record.getBreakCheckOutTime()==null){
                        record.setBreakCheckOutTime(item.getClockTime());
                        record.setBreakClockOutType(item.getType());
                    }else{
                        record.setBreakCheckInTime(item.getClockTime());
                        record.setBreakClockInType(item.getType());
                    }
                }else{
                    if(record.getBreakCheckOutTime()==null){
                        record.setBreakCheckOutTime(record.getBreakCheckInTime());
                        record.setClockOutType(item.getType());
                    }

                    record.setBreakCheckInTime(item.getClockTime());
                    record.setBreakClockInType(item.getType());
                }
            }

            if(item.getClockTime().after(record.getBreakEndTime()) && !item.getClockTime().after(record.getEndTime())){
                if(record.getBreakCheckInTime()==null){
                    record.setBreakCheckInTime(item.getClockTime());
                    record.setBreakClockInType(item.getType());
                }else{
                    record.setCheckOutTime(item.getClockTime());
                    record.setClockOutType(item.getType());
                }
            }
        }else{
            if(record.getCheckInTime()==null){
                record.setCheckInTime(item.getClockTime());
                record.setClockInType(item.getType());
            }else{
                record.setCheckOutTime(item.getClockTime());
                record.setClockOutType(item.getType());
            }
        }

        if(item.getClockTime().after(record.getEndTime())){
            record.setCheckOutTime(item.getClockTime());
            record.setClockOutType(item.getType());
        }
    }


    public void init(AttenceRule rule, Employee employee, Date checkDate){
        EmployeeItemDto dto = new EmployeeItemDto();
        BeanUtils.copyProperties(employee, dto);

        init(rule, dto, checkDate);
    }

    public void init(AttenceRule rule, EmployeeItemDto employee, Date checkDate){
        AttenceRecord attenceRecord = this;

        attenceRecord.setTenantId(employee.getTenantId());

        attenceRecord.setBeginTime(DateParseUtils.parseDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd") + " " + rule.getBeginTime()  + ":00"));

        if(rule.getBreakEnabled()!=null&&rule.getBreakEnabled()){
            attenceRecord.setBreakEnabled(true);
            attenceRecord.setBreakBeginTime(DateParseUtils.parseDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd") + " " + rule.getBreakBeginTime()  + ":00"));
            attenceRecord.setBreakEndTime(DateParseUtils.parseDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd") + " " + rule.getBreakEndTime()  + ":00"));
        }else{
            attenceRecord.setBreakEnabled(false);
        }

        attenceRecord.setEndTime(DateParseUtils.parseDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd") + " " + rule.getEndTime() + ":00"));
        attenceRecord.setBuildingDeveloperId(employee.getBuildingDeveloperId());
        attenceRecord.setBuildingSiteId(employee.getBuildingSiteId());
        attenceRecord.setEmployeeId(employee.getId());
        attenceRecord.setCheckDate(checkDate);
    }
}
