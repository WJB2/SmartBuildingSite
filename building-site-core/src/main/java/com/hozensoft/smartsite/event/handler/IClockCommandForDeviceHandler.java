package com.hozensoft.smartsite.event.handler;

import com.hozensoft.smartsite.attence.dto.AttenceDeviceDto;
import com.hozensoft.smartsite.attence.service.AttenceDeviceService;
import com.hozensoft.smartsite.iclock.service.IClockCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class IClockCommandForDeviceHandler {

    @Autowired
    private IClockCommandService iClockCommandService;

    @Autowired
    private AttenceDeviceService attenceDeviceService;

    @JmsListener(
            destination = "SS_ATTENCE_DEVICE_CREATED_EVENT",
            selector = "JMSType='SS_ATTENCE_DEVICE_CREATED_EVENT'"
    )public void onDeviceCreated(AttenceDeviceDto dto){

        iClockCommandService.initDevice(dto);
    }
}
