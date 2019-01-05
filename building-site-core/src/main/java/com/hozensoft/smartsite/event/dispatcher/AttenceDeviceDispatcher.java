package com.hozensoft.smartsite.event.dispatcher;

import com.hozensoft.smartsite.attence.dto.AttenceDeviceDto;
import com.hozensoft.smartsite.attence.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class AttenceDeviceDispatcher {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void dispatchAttenceDeviceCreatedEvent(AttenceDeviceDto dto) {
        this.jmsTemplate.convertAndSend("SS_ATTENCE_DEVICE_CREATED_EVENT", dto, new MessagePostProcessor() {
            public Message postProcessMessage(Message message) throws JMSException {
                message.setJMSType("SS_ATTENCE_DEVICE_CREATED_EVENT");

                return message;
            }
        });
    }
}
