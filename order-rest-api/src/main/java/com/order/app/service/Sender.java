package com.order.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.app.modal.OrderDetailsForMQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {

    @Autowired
    private JmsTemplate jmsTemplate;
    public void sendEmailDetailsToMQ(OrderDetailsForMQ orderDetailsForMQ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String message = objectMapper.writeValueAsString(orderDetailsForMQ);
        System.err.println(message);
        jmsTemplate.convertAndSend("new-order-notification", message);



    }
}
