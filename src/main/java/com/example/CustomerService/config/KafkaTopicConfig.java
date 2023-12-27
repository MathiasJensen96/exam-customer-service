package com.example.CustomerService.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic customerTopic() {
        return TopicBuilder.name("new_customer")
                .replicas(1)
                .partitions(3)
                .build();
    }
    @Bean
    public NewTopic newOrderPlacedTopic() {
        return TopicBuilder.name("new_order_placed")
                .build();
    }
    @Bean
    public NewTopic customerNotificationTopic() {
        return TopicBuilder.name("customer_notification")
                .build();
    }
}