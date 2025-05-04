package com.example.havakirliligiproje.Service.Concrete;

import com.example.havakirliligiproje.Dto.Request.AnomalyRequest;
import com.example.havakirliligiproje.Kafka.Producer;
import com.example.havakirliligiproje.Entity.Quality;
import com.example.havakirliligiproje.Repository.QualityRepository;
import com.example.havakirliligiproje.Service.Abstract.QualityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = "heatmapCache")
public class QualityServiceImpl implements QualityService {
    private final QualityRepository airQualityRepository;
    private final Producer producer;
    private final whoStandardService whoStandardsService;
    private final zAnomalyService statisticalAnomalyService;
    private final tempAnomalyService temporalAnomalyService;

    public QualityServiceImpl(QualityRepository airQualityRepository, Producer producer, whoStandardService whoStandardService, zAnomalyService zAnomalyService, tempAnomalyService tempAnomalyService) {
        this.airQualityRepository = airQualityRepository;
        this.producer = producer;
        this.whoStandardsService = whoStandardService;
        this.statisticalAnomalyService = zAnomalyService;
        this.temporalAnomalyService = tempAnomalyService;
    }

    @Override
    public List<Quality> getAllAirQualityData() {
        return airQualityRepository.findAll();
    }

    @Override
    public List<Quality> getAirQualityByLocation(String location) {
        return airQualityRepository.findByLocation(location);
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
        dto.setDate(airQuality.getDate());
        dto.setTimestamp(airQuality.getTimestamp().atZone(ZoneId.systemDefault()).toInstant());

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
        AnomalyRequest dto = new AnomalyRequest();
        dto.setLocation(data.getLocation());
        dto.setPm25(data.getPm25());
        dto.setPm10(data.getPm10());
        dto.setTimestamp(data.getTimestamp());
        dto.setAnomalyType(type);
        dto.setQualityData(data);

        return dto;
    }

    private String determineAnomalyType(Quality data, List<Quality> historicalData) {
        if (whoStandardsService.dangerousLevel(data)) return "WHO_THRESHOLD";
        if (statisticalAnomalyService.isZAnomaly(data, historicalData)) return "Z_SCORE";
        return "TEMPORAL_SPIKE";
    }

    @Override
    public List<AnomalyRequest> checkRegionalAnomalies(double latitude, double longitude, double radiusKm) {
        Instant cutoffTime = Instant.now().minus(24, ChronoUnit.HOURS);

        // Tüm 24 saatlik veriyi alıyoruz
        List<Quality> allData = airQualityRepository.findLast24Hours(cutoffTime);

        // Gerçek mesafeyi hesaplayarak bölgesel verileri filtreliyoruz
        List<Quality> regionalData = allData.stream()
                .filter(data -> haversine(latitude, longitude, data.getLatitude(), data.getLongitude()) <= radiusKm)
                .toList();

        // Anomalileri tespit ediyoruz
        return regionalData.stream()
                .filter(data -> isAnomaly(data, regionalData))
                .map(data -> createAnomalyDTO(data, regionalData))
                .toList();
    }

    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }


    @Override
    public Map<String, Object> getHeatmapData(
            Double minLat, Double maxLat,
            Double minLon, Double maxLon,
            Integer zoomLevel) {

        try {
            double gridSize = calculateGridSize(zoomLevel);
            List<Object[]> gridData = airQualityRepository.findHeatmapData(
                    minLat, maxLat, minLon, maxLon, gridSize
            );

            List<Map<String, Object>> features = new ArrayList<>();

            for (Object[] row : gridData) {
                try {
                    Double lon = ((Number) row[0]).doubleValue();
                    Double lat = ((Number) row[1]).doubleValue();
                    Double pm25 = ((Number) row[2]).doubleValue();

                    Map<String, Object> feature = new HashMap<>();
                    feature.put("type", "Feature");

                    Map<String, Object> geometry = new HashMap<>();
                    geometry.put("type", "Point");
                    geometry.put("coordinates", Arrays.asList(lon, lat));

                    Map<String, Object> properties = new HashMap<>();
                    properties.put("pm25", pm25);
                    properties.put("intensity", normalizeValue(pm25, 0, 300));

                    feature.put("geometry", geometry);
                    feature.put("properties", properties);
                    features.add(feature);
                } catch (Exception e) {
                    System.err.println("Veri dönüşüm hatası: " + e.getMessage());
                    e.printStackTrace();
                }
            }

            Map<String, Object> geoJson = new HashMap<>();
            geoJson.put("type", "FeatureCollection");
            geoJson.put("features", features);

            return geoJson;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public double calculateGridSize(int zoomLevel) {
        return switch (zoomLevel) {
            case 0, 1, 2, 3, 4, 5 -> 1.0;   // ~100km
            case 6, 7, 8 -> 0.1;            // ~10km
            case 9, 10, 11, 12 -> 0.01;     // ~1km
            default -> 0.001;               // ~100m
        };
    }

    @Override
    public double normalizeValue(double value, double min, double max) {
        return Math.min(Math.max((value - min) / (max - min), 0), 1);
    }

}
