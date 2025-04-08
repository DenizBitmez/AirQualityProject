package com.example.havakirliligiproje.Service.Concrete;

import com.example.havakirliligiproje.Dto.Request.AnomalyRequest;
import com.example.havakirliligiproje.Kafka.Producer;
import com.example.havakirliligiproje.Entity.Quality;
import com.example.havakirliligiproje.Repository.QualityRepository;
import com.example.havakirliligiproje.Service.Abstract.QualityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QualityServiceImpl implements QualityService {
    private final QualityRepository airQualityRepository;
    private final Producer producer;
    private final whoStandardService whoStandardsService;
    private final zAnomalyService statisticalAnomalyService;
    private final tempAnomalyService temporalAnomalyService;
    private final NotificationService notificationService;

    public QualityServiceImpl(QualityRepository airQualityRepository, Producer producer, whoStandardService whoStandardService, zAnomalyService zAnomalyService, tempAnomalyService tempAnomalyService, NotificationService notificationService) {
        this.airQualityRepository = airQualityRepository;
        this.producer = producer;
        this.whoStandardsService = whoStandardService;
        this.statisticalAnomalyService = zAnomalyService;
        this.temporalAnomalyService = tempAnomalyService;
        this.notificationService = notificationService;
    }

    @Override
    public List<Quality> getAllAirQualityData() {
        return airQualityRepository.findAll();
    }

    @Override
    public Quality getAirQualityByLocation(String location) {
        return airQualityRepository.findByLocation(location).orElse(null);
    }

    @Override
    public Quality saveAirQualityData(Quality airQuality) {
        return airQualityRepository.save(airQuality);
    }

    @Override
    @Async
    public void addAirQualityData(Quality airQuality) throws JsonProcessingException {
        airQualityRepository.save(airQuality);

        Quality dto = new Quality();
        dto.setLocation(airQuality.getLocation());
        dto.setPm25(airQuality.getPm25());
        dto.setPm10(airQuality.getPm10());
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

    @Override
    public List<Quality> getLast24HoursDataByLocation(String location) {
        return airQualityRepository.findLast24HoursByLocation(location);
    }

    @Override
    public List<Quality> getDataByDateRange(String location, String startDate, String endDate) {
        LocalDateTime start = LocalDateTime.parse(startDate, DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime end = LocalDateTime.parse(endDate, DateTimeFormatter.ISO_DATE_TIME);

        return airQualityRepository.findByLocationAndTimestampBetween(
                location,
                start.atZone(ZoneId.systemDefault()).toInstant(),
                end.atZone(ZoneId.systemDefault()).toInstant()
        );
    }

    @Override
    public List<Quality> getLast24HoursData(String location) {
        Instant end = Instant.now();
        Instant start = end.minus(24, ChronoUnit.HOURS);

        return airQualityRepository.findByLocationAndTimestampBetween(
                location,
                start,
                end
        );
    }

    // 3. Anomalileri tespit etme (Controller'da kullanılan ana metod)
    @Override
    public List<AnomalyRequest> detectAnomalies(String location, String startDate, String endDate) {
        List<Quality> dataInRange = getDataByDateRange(location, startDate, endDate);
        List<Quality> last24HoursData = getLast24HoursData(location);

        return dataInRange.stream()
                .filter(data -> isAnomaly(data, last24HoursData))
                .map(data -> createAnomalyDTO(data, last24HoursData))
                .collect(Collectors.toList());
    }
    private boolean isAnomaly(Quality data, List<Quality> historicalData) {
        return whoStandardsService.dangerousLevel(data) ||
                statisticalAnomalyService.isZAnomaly(data, historicalData) ||
                temporalAnomalyService.isTemporalAnomaly(data, historicalData);
    }

    private AnomalyRequest createAnomalyDTO(Quality data, List<Quality> historicalData) {
        String type = determineAnomalyType(data, historicalData);
        return new AnomalyRequest();
    }

    private String determineAnomalyType(Quality data, List<Quality> historicalData) {
        if (whoStandardsService.dangerousLevel(data)) return "WHO_THRESHOLD";
        if (statisticalAnomalyService.isZAnomaly(data, historicalData)) return "Z_SCORE";
        return "TEMPORAL_SPIKE";
    }

    @Override
    public List<AnomalyRequest> checkRegionalAnomalies(double latitude, double longitude, double radiusKm) {
        Instant cutoffTime = Instant.now().minus(24, ChronoUnit.HOURS);
        List<Quality> regionalData = airQualityRepository.findRegionalLast24Hours(latitude, longitude, radiusKm, cutoffTime);

        return regionalData.stream()
                .filter(data -> isRegionalAnomaly(data, regionalData))
                .map(data -> createAnomalyDTO(data, regionalData))
                .collect(Collectors.toList());
    }
    private boolean isRegionalAnomaly(Quality data, List<Quality> regionalData) {
        double regionalAvg = regionalData.stream()
                .mapToDouble(Quality::getPm25)
                .average()
                .orElse(0);

        return Math.abs(data.getPm25() - regionalAvg) > (regionalAvg * 0.5);
    }
}
