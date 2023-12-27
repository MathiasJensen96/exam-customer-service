package com.example.CustomerService.model.enums;

import lombok.ToString;

@ToString
public enum Topic {
    customer_notification,
    new_customer,
    new_order_placed,
    order_accepted,
    order_ready,
    order_cancelled,
    order_picked_up,
    order_delivered,
    order_claimed
}