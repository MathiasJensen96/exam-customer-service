package com.example.CustomerService.service;

import com.example.CustomerService.model.dto.CustomerNotification;
import com.example.CustomerService.model.dto.NewCustomer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.CustomerService.model.enums.Topic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void newCustomerEvent(NewCustomer newCustomer) {
        String newCustomerTopic = String.valueOf(Topic.new_customer);

        kafkaTemplate.send(newCustomerTopic, newCustomer);
    }

    public void customerNotificationEvent(CustomerNotification customerNotification) {
        kafkaTemplate.send(Topic.customer_notification.name(), customerNotification);
    }
}