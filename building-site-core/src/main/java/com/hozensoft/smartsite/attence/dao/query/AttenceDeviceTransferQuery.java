package com.hozensoft.smartsite.attence.dao.query;

import com.hozensoft.smartsite.attence.dto.AttenceDeviceTransferDto;
import com.hozensoft.smartsite.attence.dto.AttenceDeviceTransferItemDto;
import com.hozensoft.smartsite.attence.dto.AttenceDeviceTransferQueryDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AttenceDeviceTransferQuery {

    AttenceDeviceTransferDto findAttenceDeviceTransferById(@Param("attenceDeviceId") String attenceDeviceId);

    List<AttenceDeviceTransferItemDto> findAttenceDeviceTransferList(AttenceDeviceTransferQueryDto params);
}
