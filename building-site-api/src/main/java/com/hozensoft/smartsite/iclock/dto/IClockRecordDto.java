package com.hozensoft.smartsite.iclock.dto;

import com.hozensoft.smartsite.enumeration.ClockTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class IClockRecordDto {

    private String id;

    private String sn;

    private String code;

    private Date clockTime;

    private ClockTypeEnum type;
}
