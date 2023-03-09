package com.rabbitmq.publisher;

import com.rabbitmq.dto.Order;
import com.rabbitmq.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        order.setId(UUID.randomUUID());
        OrderStatus orderStatus = new OrderStatus(order, "Completed", "Order successfully done!!!");
        rabbitTemplate.convertAndSend(orderExchange, orderRoutingKey, orderStatus.toString());
        return "Ok";
    }
}
