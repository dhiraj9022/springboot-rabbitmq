package com.rabbitmq.controller;

import com.rabbitmq.dto.User;
import com.rabbitmq.publisher.UserProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class OrderController {

    @Autowired
    private UserProducer userProducer;

    @PostMapping("/publish")
    public String processOrder(@RequestBody User user){
        userProducer.sendOrder(user);
        return  "order successfully done !!!";
    }
}
