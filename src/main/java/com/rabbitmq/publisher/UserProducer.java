package com.rabbitmq.publisher;

import com.rabbitmq.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserProducer {

    @Value("${order-routing-key}")
    private String orderRoutingKey;

    @Value("${order-exchange}")
    private String orderExchange;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendOrder(User user){
        log.info(String.format("Order process : %s",user.toString()));
        rabbitTemplate.convertAndSend(orderExchange, orderRoutingKey, user.toString());
    }
}
