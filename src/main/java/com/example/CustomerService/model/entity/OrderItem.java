package com.example.CustomerService.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "order_item")
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int menuItemId;
    private int quantity;

    public OrderItem(CartItem cartItem) {
        this.menuItemId = cartItem.getMenuItemId();
        this.quantity = cartItem.getQuantity();
    }

    public OrderItem(OrderItem dto) {
        this.menuItemId = dto.getMenuItemId();
        this.quantity = dto.getQuantity();
    }
}