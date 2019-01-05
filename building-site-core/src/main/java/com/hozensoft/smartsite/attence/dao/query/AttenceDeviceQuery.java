package com.hozensoft.smartsite.attence.dao.query;

import com.hozensoft.smartsite.attence.dto.AttenceDeviceDto;
import com.hozensoft.smartsite.attence.dto.AttenceDeviceItemDto;
import com.hozensoft.smartsite.attence.dto.AttenceDeviceQueryDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AttenceDeviceQuery {

    AttenceDeviceDto findAttenceDeviceById(@Param("attenceDeviceId")String attenceDeviceId);

    AttenceDeviceDto findAttenceDeviceBySn(@Param("attenceDeviceSn")String attenceDeviceSn);

    List<AttenceDeviceItemDto> findAttenceDeviceList(AttenceDeviceQueryDto params);
}
