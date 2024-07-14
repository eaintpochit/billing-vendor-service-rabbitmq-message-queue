package com.vendor.biller.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "vendor.message.queue";

    public static final String EXCHANGE_NAME = "topic.exchange.vendor";


    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    @Bean
    public Binding binding(TopicExchange topicExchange, Queue queue) {
        return BindingBuilder.bind(queue).to(topicExchange).with("#");
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }


    // Define the second RabbitTemplate with SimpleMessageConverter
    public RabbitTemplate simpleRabbitTemplate(ConnectionFactory connectionFactory, SimpleMessageConverter simpleMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(simpleMessageConverter);
        return rabbitTemplate;
    }


}
