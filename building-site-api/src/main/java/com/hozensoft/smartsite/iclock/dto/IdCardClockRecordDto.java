package com.hozensoft.smartsite.iclock.dto;

import com.hozensoft.smartsite.enumeration.ClockTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class IdCardClockRecordDto {

    private String id;

    private String employeeName;

    private String employeeIdCardNo;

    private String deviceSn;

    private Date clockingTime;

    private ClockTypeEnum type;
}
