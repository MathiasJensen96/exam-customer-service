package com.example.CustomerService.model.dto;

import com.example.CustomerService.model.entity.Cart;
import com.example.CustomerService.model.entity.CartItem;
import com.example.CustomerService.model.entity.OrderItem;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NewOrder {
    private int customerId;
    private int restaurantId;
    private Date createdAt;
    private boolean withDelivery;
    private List<OrderItem> items = new ArrayList<>();

    public NewOrder(Cart cart) {
        this.customerId = cart.getCustomerId();
        this.restaurantId = cart.getRestaurantId();
        this.createdAt = new Date();
        this.withDelivery = cart.isWithDelivery();
        mapCartItemToOrder(cart.getItems());
    }

    private void mapCartItemToOrder(List<CartItem> cartItemsList) {
        cartItemsList.forEach(cartItemEntity -> this.items.add(new OrderItem(cartItemEntity)));
    }
}