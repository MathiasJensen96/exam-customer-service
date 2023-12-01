package com.example.CustomerService.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class StartOrderProcess {
    private OrderHolder variables;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    @Builder
    public static class CamundaOrder {
        private String value;
        private String type;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    @Builder
    public static class OrderHolder {
        private CamundaOrder order;
    }
}
