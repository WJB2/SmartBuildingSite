package com.hozensoft.smartsite.iclock.service;

import com.hozensoft.smartsite.iclock.dto.IClockRecordDto;
import com.hozensoft.smartsite.iclock.dto.IdCardClockRecordDto;

import java.util.List;

public interface IClockRecordService {

    int clocking(List<IClockRecordDto> records);

    int clockingWithIdCard(List<IdCardClockRecordDto> records);
}
