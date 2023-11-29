package com.example.CustomerService.model.dto;

import com.example.CustomerService.model.entity.Customer;
import com.example.CustomerService.model.entity.CustomerOrder;
import lombok.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CustomerDTO {
    private String firstName;
    private String lastName;
    private String email;
    private int addressId;
    private String phone;
    private List<CustomerOrderDTO> customerOrderList;
    private String reason;

    public CustomerDTO(Customer customer) {
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
        this.addressId = customer.getAddressId();
        this.phone = customer.getPhone();
        this.customerOrderList = customer.getCustomerOrder()
                .stream()
                .map(order -> CustomerOrderDTO.builder()
                        .createdAt(order.getCreatedAt())
                        .deliver(order.isDeliver())
                        .deliveryPrice(order.getDeliveryPrice())
                        .orderPrice(order.getOrderPrice())
                        .restaurantId(order.getRestaurantId())
                        .systemOrderId(order.getSystemOrderId())
                        .status(order.getStatus())
                        .build())
                .toList();
    }

    public CustomerDTO(Optional<CustomerOrder> customer) {
        this.firstName = customer.get().getCustomer().getFirstName();
        this.lastName = customer.get().getCustomer().getLastName();
        this.email = customer.get().getCustomer().getEmail();
        this.addressId = customer.get().getCustomer().getAddressId();
        this.phone = customer.get().getCustomer().getPhone();
        this.customerOrderList = customer.get().getCustomer().getCustomerOrder()
                .stream()
                .map(order -> CustomerOrderDTO.builder()
                        .createdAt(order.getCreatedAt())
                        .deliver(order.isDeliver())
                        .deliveryPrice(order.getDeliveryPrice())
                        .orderPrice(order.getOrderPrice())
                        .restaurantId(order.getRestaurantId())
                        .systemOrderId(order.getSystemOrderId())
                        .status(order.getStatus())
                        .build()).toList();
    }
}