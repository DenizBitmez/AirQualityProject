package com.airquality.service.impl;

import com.airquality.repository.AirQualityRepository;
import com.airquality.service.QualityService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QualityServiceImpl implements QualityService {

    private final AirQualityRepository airQualityRepository;

    public QualityServiceImpl(AirQualityRepository airQualityRepository) {
        this.airQualityRepository = airQualityRepository;
    }

    @Override
    public Map<String, Object> getHeatmapData(
            Double minLat, Double maxLat,
            Double minLon, Double maxLon,
            Integer zoomLevel) {

        try {
            System.out.println("Service - Hesaplama başlıyor");
            System.out.println("Koordinatlar: " + minLat + "," + maxLat + "," + minLon + "," + maxLon);
            System.out.println("Zoom Level: " + zoomLevel);

            double gridSize = calculateGridSize(zoomLevel);
            System.out.println("Hesaplanan Grid Size: " + gridSize);

            List<Object[]> gridData = airQualityRepository.findHeatmapData(
                    minLat, maxLat, minLon, maxLon, gridSize
            );
            System.out.println("Veritabanından gelen veri sayısı: " + gridData.size());

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
                    System.err.println("Problemli satır: " + Arrays.toString(row));
                    e.printStackTrace();
                }
            }

            System.out.println("Oluşturulan feature sayısı: " + features.size());

            Map<String, Object> geoJson = new HashMap<>();
            geoJson.put("type", "FeatureCollection");
            geoJson.put("features", features);
            
            return geoJson;

        } catch (Exception e) {
            System.err.println("Service katmanında hata:");
            System.err.println("Hata mesajı: " + e.getMessage());
            System.err.println("Hata sınıfı: " + e.getClass().getName());
            e.printStackTrace();
            throw e;
        }
    }

    private double calculateGridSize(int zoomLevel) {
        return switch (zoomLevel) {
            case 0, 1, 2, 3, 4, 5 -> 1.0;   // ~100km
            case 6, 7, 8 -> 0.1;            // ~10km
            case 9, 10, 11, 12 -> 0.01;     // ~1km
            default -> 0.001;               // ~100m
        };
    }

    private double normalizeValue(double value, double min, double max) {
        return Math.min(Math.max((value - min) / (max - min), 0), 1);
    }
} 