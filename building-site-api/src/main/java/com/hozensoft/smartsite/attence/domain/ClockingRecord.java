package com.hozensoft.smartsite.attence.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 员工打卡记录
 */
@Getter
@Setter
public class ClockingRecord {

    /**
     * ID
     */
    private String id;

    /**
     * 设备唯一编码
     */
    private String deviceSn;

    /**
     * 人员编码
     */
    private String employeeCode;

    /**
     * 打卡时间
     */
    private Date clockingTime;
}
