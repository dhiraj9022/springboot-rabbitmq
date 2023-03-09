package com.rabbitmq.controller;

import com.rabbitmq.config.RabbitMQConfig;
import com.rabbitmq.dto.Order;
import com.rabbitmq.dto.OrderStatus;
import com.rabbitmq.dto.User;
import com.rabbitmq.publisher.UserProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class OrderPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${order-exchange}")
    private String orderExchange;

    @Value("${order-routing-key}")
    private String orderRoutingKey;

    @PostMapping("/publish")
    public String processOrder(@RequestBody Order order){
        OrderStatus orderStatus = new OrderStatus(order, "Completed", "Order successfully done!!!");
        rabbitTemplate.convertAndSend(orderExchange, orderRoutingKey, orderStatus);
        return "Ok";
    }
}
