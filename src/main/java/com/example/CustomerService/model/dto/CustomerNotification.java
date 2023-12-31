package com.example.CustomerService.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerNotification {
    private String email;
    private String subject;
    private String message;
}