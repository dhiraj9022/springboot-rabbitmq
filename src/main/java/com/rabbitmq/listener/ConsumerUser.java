package com.rabbitmq.listener;

import com.rabbitmq.config.RabbitMQConfig;
import com.rabbitmq.dto.Order;
import com.rabbitmq.dto.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerUser {

    @RabbitListener(queues = {"#{order-queue}"})
    public void receiveMessageOrder(User user){
        System.out.println("Message recieved from queue : " + user.getOrder());
    }
}
