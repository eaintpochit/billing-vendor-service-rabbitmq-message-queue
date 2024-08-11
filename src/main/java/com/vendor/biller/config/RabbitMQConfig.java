package com.vendor.biller.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "message.queue";
    public static final String VENDOR_PROMO_MSG = "promotion.message";
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
    public Queue promoQueue() {
        return QueueBuilder.durable(VENDOR_PROMO_MSG).build();
    }

    @Bean
    public Binding queueBinding(TopicExchange topicExchange, Queue queue) {
        return BindingBuilder.bind(queue).to(topicExchange).with(QUEUE_NAME);
    }

    @Bean
    public RabbitTemplate billRequestRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        // Timeout after 30 seconds
        template.setReplyTimeout(30000);
        return template;
    }

    @Bean
    public Binding promoQueueBinding(TopicExchange topicExchange, Queue promoQueue) {
        return BindingBuilder.bind(promoQueue).to(topicExchange).with(VENDOR_PROMO_MSG);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }


}
