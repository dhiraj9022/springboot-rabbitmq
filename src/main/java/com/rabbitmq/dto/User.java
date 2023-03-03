package com.rabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private long id;
    private String customerName;
    private String orderItemName;
    private Double price;
    private long quantity;
    private UUID orderId;
    private Order order;

}
