package com.rabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
public class User {

    private long id;
    private String customerName;
    private String orderItemName;

    private String orderStatus;

}
