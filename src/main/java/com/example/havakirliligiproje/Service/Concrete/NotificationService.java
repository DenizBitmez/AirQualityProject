package com.example.havakirliligiproje.Service.Concrete;

import com.example.havakirliligiproje.Dto.Request.AnomalyRequest;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Service
public class NotificationService {
    private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendAnomalyAlert(String location, AnomalyRequest anomaly) {
        messagingTemplate.convertAndSend(
                "/topic/anomalies/" + location,
                Map.of(
                        "pm25", anomaly.getPm25(),
                        "pm10", anomaly.getPm10(),
                        "type", anomaly.getAnomalyType()
                )
        );
    }

    private String getSeverityByType(String anomalyType) {
        return switch (anomalyType) {
            case "WHO_THRESHOLD" -> "CRITICAL";
            case "Z_SCORE" -> "HIGH";
            default -> "MEDIUM";
        };
    }
}
