package com.rabbitmq.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${order-queue}")
    private  String orderQueue;

    @Value("${order-exchange}")
    private String orderExchange;

    @Value("${order-routing-key}")
    private String orderRoutingKey;

    @Bean
    public Queue queue(){
        return new Queue(orderQueue);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(orderExchange);
    }

    @Bean
    public Binding binding(Queue queue , TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(orderRoutingKey);
    }

    @Bean
    public MessageConverter converter(){
        return  new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return  rabbitTemplate;
    }
}
