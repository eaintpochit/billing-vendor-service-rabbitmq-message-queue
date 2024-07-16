package com.vendor.biller.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vendor.biller.config.RabbitMQConfig;
import com.vendor.biller.dto.BillerRequestDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillerRequestService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public String send(BillerRequestDto billerRequest) {
        String msg = "";
        try {
            String json = objectMapper.writeValueAsString(billerRequest);
            rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, json);
            msg = "request sent";
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            msg = "request cannot sent";
        }
        return msg;
    }
}
