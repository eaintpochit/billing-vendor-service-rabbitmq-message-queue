package com.vendor.biller.service;

import com.vendor.biller.config.RabbitMQConfig;
import jakarta.mail.*;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${email.sender.host}")
    private String host;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME, containerFactory = "rabbitListenerContainerFactory")
    public void receiver(String message) throws InterruptedException {
        System.out.println(message);
       sendEmail(message);
    }



    public void sendEmail(String topicMessage) {
        if ("success".equals(topicMessage)) {

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("hninpwintaung001@gmail.com");
            message.setSubject("No Reply Email");
            message.setText("This is a no-reply email. Please do not reply to this email.");
            message.setFrom(host);
            message.setReplyTo("no-reply@example.com");

            javaMailSender.send(message);
            System.out.println("Sent message successfully....");
        }
    }
}
