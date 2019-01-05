package com.hozensoft.smartsite.iclock.dao.repo;

import com.hozensoft.smartsite.iclock.domain.IClockCommand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IClockCommandRepo {

    int addIClockCommandList(@Param("commands") List<IClockCommand> commands);

    int deleteIClockCommandById(@Param("commandIds")List<String> commandIds);
}
