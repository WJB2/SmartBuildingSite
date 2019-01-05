package com.hozensoft.smartsite.iclock.service;


import com.hozensoft.smartsite.attence.dto.AttenceDeviceDto;
import com.hozensoft.smartsite.attence.dto.EmployeeDto;
import com.hozensoft.smartsite.iclock.domain.IClockCommand;
import com.hozensoft.smartsite.iclock.dto.IClockCommandItemDto;

import java.util.List;

public interface IClockCommandService {

    List<IClockCommandItemDto> findIClockCommandBySn(String sn);

    void addIClockCommand(List<IClockCommand> iClockCommand);

    void deleteIClockCommandById( List<String> commandIdList);

    void syncCommand(String sn,String command);

    void initDevice(AttenceDeviceDto attenceDevice);

    void createEmployee(EmployeeDto employeeDto);
}
