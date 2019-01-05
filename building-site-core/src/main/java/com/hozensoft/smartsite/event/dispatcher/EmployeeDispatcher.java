package com.hozensoft.smartsite.event.dispatcher;

import com.hozensoft.smartsite.attence.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class EmployeeDispatcher {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void dispatchEmployeeCreatedEvent(EmployeeDto dto) {
        this.jmsTemplate.convertAndSend("SS_EMPLOYEE_CREATED_EVENT", dto, new MessagePostProcessor() {
            public Message postProcessMessage(Message message) throws JMSException {
                message.setJMSType("SS_EMPLOYEE_CREATED_EVENT");
                return message;
            }
        });
    }

    public void dispatchEmployeeDeletedEvent(EmployeeDto dto) {
        this.jmsTemplate.convertAndSend("SS_EMPLOYEE_DELETED_EVENT", dto, new MessagePostProcessor() {
            public Message postProcessMessage(Message message) throws JMSException {
                message.setJMSType("SS_EMPLOYEE_DELETED_EVENT");
                return message;
            }
        });
    }
}
