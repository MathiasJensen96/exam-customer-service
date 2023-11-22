package com.example.CustomerService.model.dto;

import com.example.CustomerService.model.entity.Customer;
import lombok.*;

import java.util.List;

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

    public CustomerDTO(Customer customer) {
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
        this.addressId = customer.getAddressId();
        this.phone = customer.getPhone();
    }
}