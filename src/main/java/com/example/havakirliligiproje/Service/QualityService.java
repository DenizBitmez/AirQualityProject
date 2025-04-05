package com.example.havakirliligiproje.Service;

import com.example.havakirliligiproje.Kafka.Producer;
import com.example.havakirliligiproje.Model.Quality;
import com.example.havakirliligiproje.Repository.QualityRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QualityService {
    private final QualityRepository airQualityRepository;
    private final Producer producer;


    public QualityService(QualityRepository airQualityRepository, Producer producer) {
        this.airQualityRepository = airQualityRepository;
        this.producer = producer;
    }

    public List<Quality> getAllAirQualityData() {
        return airQualityRepository.findAll();
    }

    public Quality getAirQualityByLocation(String location) {
        return airQualityRepository.findById(location).orElse(null);
    }

    public Quality saveAirQualityData(Quality airQuality) {
        return airQualityRepository.save(airQuality);
    }

    public List<Quality> findAnomalies(String location, String startDate, String endDate) {
        List<Quality> allData = airQualityRepository.findByLocationAndDateBetween(location, startDate, endDate);

        return allData.stream()
                .filter(q -> q.getPm25() > 100)
                .collect(Collectors.toList());
    }

    @Async
    public void addAirQualityData(Quality airQuality) throws JsonProcessingException {
        airQualityRepository.save(airQuality);

        Quality dto = new Quality();
        dto.setLocation(airQuality.getLocation());
        dto.setPm25(airQuality.getPm25());
        dto.setPm10(airQuality
                .getPm10());
        dto.setNo2(airQuality.getNo2());
        dto.setSo2(airQuality.getSo2());
        dto.setO3(airQuality.getO3());
        dto.setTimestamp(airQuality.getTimestamp());

        producer.sendMessage("pollution-data-topic", new ObjectMapper().writeValueAsString(dto));
    }

    public List<Quality> getPollutionByRegion(double latitude, double longitude) {
        double tolerance = 0.05;

        return airQualityRepository.findByLatitudeBetweenAndLongitudeBetween(
                latitude - tolerance,
                latitude + tolerance,
                longitude - tolerance,
                longitude + tolerance
        );
    }
}
