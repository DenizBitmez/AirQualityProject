package com.example.havakirliligiproje.Kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @KafkaListener(topics = "pollution-data-topic", groupId = "group_id")
    public void consume(String message) {
        System.out.println("Veri alındı: " + message);
    }
}
