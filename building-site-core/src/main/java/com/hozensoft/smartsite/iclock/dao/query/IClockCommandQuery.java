package com.hozensoft.smartsite.iclock.dao.query;

import com.hozensoft.smartsite.iclock.dto.IClockCommandItemDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IClockCommandQuery {

    List<IClockCommandItemDto> findIClockCommandListBySn(@Param("sn") String sn);
}
