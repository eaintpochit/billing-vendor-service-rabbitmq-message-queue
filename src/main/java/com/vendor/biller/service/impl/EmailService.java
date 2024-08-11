package com.vendor.biller.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vendor.biller.config.RabbitMQConfig;
import com.vendor.biller.dto.EmailDto;
import com.vendor.biller.dto.PromotionMessageDto;
import com.vendor.biller.service.IEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EmailService implements IEmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${email.sender.host}")
    private String host;

    @Value("${email.reply.to}")
    private String replyMail;


    @RabbitListener(queues = RabbitMQConfig.VENDOR_PROMO_MSG)
    @Override
    public void sendBulkEmail(String message) {

        ObjectMapper objectMapper = new ObjectMapper();

        List<String> emailRecierverList = new ArrayList<>();
        emailRecierverList.add("eaintpochit.eaint@gmail.com");

        try{
            PromotionMessageDto promotionMessageDto = objectMapper.readValue(message,
                    PromotionMessageDto.class);
            log.info(promotionMessageDto.getTopic()+"::::"+promotionMessageDto.getContext());

            if(!emailRecierverList.isEmpty()){
                for(String receiverEmailAddress : emailRecierverList){
                    EmailDto email = new EmailDto();
                    email.setReceiverMail(receiverEmailAddress);
                    email.setSubject(promotionMessageDto.getTopic());
                    email.setBody(promotionMessageDto.getContext());
                    email.setReplyTo(replyMail);
                    sendMail(email);
                }

                // email.setReceiverMail(e);
            }
        }catch (Exception je){
            je.printStackTrace();
        }

    }


    public void sendMail(EmailDto email){

        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email.getReceiverMail());
            message.setSubject(email.getSubject());
            message.setText(email.getBody());
            message.setFrom(host);
            // message.setReplyTo("no-reply@example.com");

            javaMailSender.send(message);
        }catch (Exception e){}

    }
}
