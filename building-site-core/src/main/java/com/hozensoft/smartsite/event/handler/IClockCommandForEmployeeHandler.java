package com.hozensoft.smartsite.event.handler;

import com.hozensoft.smartsite.attence.dto.AttenceDeviceItemDto;
import com.hozensoft.smartsite.attence.dto.AttenceDeviceQueryDto;
import com.hozensoft.smartsite.attence.dto.EmployeeDto;
import com.hozensoft.smartsite.attence.service.AttenceDeviceService;
import com.hozensoft.smartsite.iclock.domain.IClockCommand;
import com.hozensoft.smartsite.iclock.service.IClockCommandService;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.system.utils.bean.ContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class IClockCommandForEmployeeHandler {

    @Autowired
    private IClockCommandService iClockCommandService;

    @Autowired
    private AttenceDeviceService attenceDeviceService;

    @JmsListener(
            destination = "SS_EMPLOYEE_CREATED_EVENT",
            selector = "JMSType='SS_EMPLOYEE_CREATED_EVENT'"
    )
    public void onEmployeeCreated(EmployeeDto dto) throws UnsupportedEncodingException {

        iClockCommandService.createEmployee(dto);
    }

    @JmsListener(
            destination = "SS_EMPLOYEE_DELETED_EVENT",
            selector = "JMSType='SS_EMPLOYEE_DELETED_EVENT'"
    )
    public void onEmployeeDeleted(EmployeeDto dto) {

        AttenceDeviceQueryDto params = AttenceDeviceQueryDto.builder()
                .tenantId(dto.getTenantId())
                .buildingDeveloperId(Arrays.asList(dto.getBuildingDeveloperId()))
                .buildingSiteId(Arrays.asList(dto.getBuildingSiteId()))
                .deletedFlag(false).build();

        List<AttenceDeviceItemDto> devices = attenceDeviceService.findAttenceDeviceList(ContextHolder.builder().tenantId(dto.getTenantId()).build(), params, new Limitable());

        List<IClockCommand> commands = new ArrayList<>(devices.size());

        long value = System.currentTimeMillis();

        for(int i=0; i<devices.size(); i++){
            IClockCommand command = new IClockCommand();

            command.setId(value*100+i + "");
            command.setSn(devices.get(i).getSn());

            StringBuffer cmd = new StringBuffer();
            cmd.append("C:");
            cmd.append(value*100+i + ":DATA DELETE USERINFO PIN=" + dto.getCode() + "\t");
            cmd.append("Name=" + dto.getName() + "\t");
            cmd.append("Pri=0\t");
            command.setCommand(cmd.toString());

            commands.add(command);
        }

        iClockCommandService.addIClockCommand(commands);
    }
}
