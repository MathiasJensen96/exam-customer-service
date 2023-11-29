package com.example.CustomerService.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NewCustomer {
    private String firstName;
    private String lastName;
    private String email;
    private int addressId;
    private String phone;
}