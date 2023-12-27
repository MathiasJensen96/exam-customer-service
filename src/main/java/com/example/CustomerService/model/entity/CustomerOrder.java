package com.example.CustomerService.model.entity;

import com.example.CustomerService.model.dto.NewCustomerOrder;
import com.example.CustomerService.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "customer_order")
public class CustomerOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(targetEntity = OrderItem.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    @JoinColumn(name = "coi_fk", referencedColumnName = "id")
    @Column(name = "order_items")
    private List<OrderItem> orderItems = new ArrayList<>();
    @Column(name = "created_time", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @Column(name = "deliver", nullable = false)
    private boolean deliver;
    @Column(name = "delivery_price", nullable = false)
    private double deliveryPrice;
    @Column(name = "order_price", nullable = false)
    private double orderPrice;
    @Column(name = "restaurant_id", nullable = false)
    private int restaurantId;
    @Column(name = "system_order_id", nullable = false, unique = true)
    private int systemOrderId;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    Customer customer;

    public CustomerOrder(NewCustomerOrder dto) {
        addOrderItemToOrderItem(dto.getItems());
        this.createdAt = dto.getCreatedAt();
        this.deliver = dto.isWithDelivery();
        this.deliveryPrice = dto.getDeliveryPrice();
        this.orderPrice = dto.getTotalPrice();
        this.restaurantId = dto.getRestaurantId();
        this.systemOrderId = dto.getId();
        this.status = OrderStatus.PENDING;
    }

    private void addOrderItemToOrderItem(List<OrderItem> orderItems) {
        orderItems.forEach(orderItem ->
                this.orderItems.add(new OrderItem(orderItem))
        );
    }

    public CustomerOrder(Cart cart) {
        this.restaurantId = cart.getCustomerId();
        this.restaurantId = cart.getRestaurantId();
        addCartItemsToOrderItems(cart.getItems());
        this.orderPrice = cart.getTotalPrice();
        this.deliver = cart.isWithDelivery();
    }

    private void addCartItemsToOrderItems(List<CartItem> cartItems) {
        cartItems.forEach(cartItem ->
                this.orderItems.add(new OrderItem(cartItem))
        );
    }
}