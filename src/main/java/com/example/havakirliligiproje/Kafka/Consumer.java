package com.example.havakirliligiproje.Kafka;

import com.example.havakirliligiproje.Entity.Quality;
import com.example.havakirliligiproje.Repository.QualityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    private final QualityRepository qualityRepository;

    public Consumer(QualityRepository qualityRepository) {
        this.qualityRepository = qualityRepository;
    }
    @KafkaListener(topics = "air-quality-topic", groupId = "group_id")
    public void consume(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Quality quality = objectMapper.readValue(message, Quality.class);
            qualityRepository.save(quality);
            System.out.println("Kaydedildi: " + quality.getId());
        } catch (Exception e) {
            System.out.println("JSON parse hatası: " + e.getMessage());
        }
    }
}
