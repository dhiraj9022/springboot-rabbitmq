package com.rabbitmq.publisher;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserConsumer {

    @RabbitListener(queues = {"${order-queue}"})
    public void receiveMessageOrder(String message){
        log.info(String.format("Received message -> %s", message));
    }
}
