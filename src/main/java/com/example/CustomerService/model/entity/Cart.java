package com.example.CustomerService.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "cart")
public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "customer_id", unique = true, nullable = false)
    private int customerId;
    @Column(name = "restaurant_id")
    private int restaurantId;
    @OneToMany(targetEntity = CartItem.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    @JoinColumn(name = "ci_fk", referencedColumnName = "customer_id")
    private List<CartItem> items = new ArrayList<>();
    @Column(name = "total_price")
    private double totalPrice;
    @Column(name = "delivery")
    private boolean withDelivery;
}