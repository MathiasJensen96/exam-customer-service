package com.example.CustomerService.model.dto;

import com.example.CustomerService.model.entity.OrderItem;
import com.example.CustomerService.model.enums.OrderStatus;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NewCustomerOrder {
    private int id;
    private int restaurantId;
    private int customerId;
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    private OrderStatus status;
    private boolean withDelivery;
    private double deliveryPrice;
    private double totalPrice;
    private List<OrderItem> items;
}
