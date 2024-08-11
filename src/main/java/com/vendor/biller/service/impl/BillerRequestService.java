package com.vendor.biller.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vendor.biller.config.RabbitMQConfig;
import com.vendor.biller.dto.BillerRequestDto;
import com.vendor.biller.service.IBillerRequestService;
import com.vendor.biller.util.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BillerRequestService implements IBillerRequestService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ResponseEntity sendBillRequest(BillerRequestDto billerRequest) {
        String msg = "";
        try {
            String json = objectMapper.writeValueAsString(billerRequest);
            Object response = rabbitTemplate.convertSendAndReceive(RabbitMQConfig.QUEUE_NAME, json);
            log.info("Request Sent Successful.");
            if(response!=null){
                log.info(response.toString());

                /**
                * do business concern
                * */
            }
            return new ResponseEntity(Message.SUCCESS.getDescription(), HttpStatus.OK);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.info("Request cannot sent.");
            return new ResponseEntity(Message.FAIL.getDescription(), HttpStatus.OK);
        }

    }
}
