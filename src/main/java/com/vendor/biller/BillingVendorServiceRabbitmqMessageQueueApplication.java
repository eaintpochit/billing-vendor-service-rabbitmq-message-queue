package com.vendor.biller;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class BillingVendorServiceRabbitmqMessageQueueApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingVendorServiceRabbitmqMessageQueueApplication.class, args);
    }

}
