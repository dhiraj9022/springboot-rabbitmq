package com.rabbitmq.controller;

import com.rabbitmq.config.RabbitMQConfig;
import com.rabbitmq.dto.Order;
import com.rabbitmq.dto.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class OrderPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate ;

    @Autowired
    private RabbitMQConfig rabbitMQConfig;

    @GetMapping
    public String processOrder(){
        User user = new User(1, "ABC","IceCream",500.0,10,UUID.randomUUID(),Order.COMPLETED);
        rabbitTemplate.convertAndSend(String.valueOf(new RabbitMQConfig().exchange()), new RabbitMQConfig().getOrderRoutingKey(), user);
        return  "order successfully done !!!";
    }
}
