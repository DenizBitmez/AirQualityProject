package com.example.havakirliligiproje.Service.Concrete;

import com.example.havakirliligiproje.Kafka.Producer;
import com.example.havakirliligiproje.Entity.Quality;
import com.example.havakirliligiproje.Repository.QualityRepository;
import com.example.havakirliligiproje.Service.Abstract.QualityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QualityServiceImpl implements QualityService {
    private final QualityRepository airQualityRepository;
    private final Producer producer;


    public QualityServiceImpl(QualityRepository airQualityRepository, Producer producer) {
        this.airQualityRepository = airQualityRepository;
        this.producer = producer;
    }

    @Override
    public List<Quality> getAllAirQualityData() {
        return airQualityRepository.findAll();
    }

    @Override
    public Quality getAirQualityByLocation(String location) {
        return airQualityRepository.findById(location).orElse(null);
    }

    @Override
    public Quality saveAirQualityData(Quality airQuality) {
        return airQualityRepository.save(airQuality);
    }

    @Override
    public List<Quality> findAnomalies(String location, String startDate, String endDate) {
        List<Quality> allData = airQualityRepository.findByLocationAndDateBetween(location, startDate, endDate);

        return allData.stream()
                .filter(q -> q.getPm25() > 100)
                .collect(Collectors.toList());
    }

    @Override
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

    @Override
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
