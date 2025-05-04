package com.airquality.controller;

import com.airquality.service.QualityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quality")
@CrossOrigin(origins = "*")  // CORS için ekledik
public class QualityController {

    private final QualityService qualityService;

    public QualityController(QualityService qualityService) {
        this.qualityService = qualityService;
    }

    private double calculateGridSize(int zoomLevel) {
        if (zoomLevel <= 5) return 1.0;      // ~100km
        if (zoomLevel <= 8) return 0.1;      // ~10km
        if (zoomLevel <= 12) return 0.01;    // ~1km
        return 0.001;                        // ~100m
    }

    @GetMapping("/heatmap")
    public ResponseEntity<Map<String, Object>> getHeatmapData(
            @RequestParam Double minLat,
            @RequestParam Double maxLat,
            @RequestParam Double minLon,
            @RequestParam Double maxLon,
            @RequestParam Integer zoomLevel) {
        try {
            System.out.println("Controller - Gelen parametreler:");
            System.out.println("minLat: " + minLat);
            System.out.println("maxLat: " + maxLat);
            System.out.println("minLon: " + minLon);
            System.out.println("maxLon: " + maxLon);
            System.out.println("zoomLevel: " + zoomLevel);

            Map<String, Object> heatmapData = qualityService.getHeatmapData(
                    minLat, maxLat, minLon, maxLon, zoomLevel
            );

            System.out.println("Veri başarıyla alındı. Feature sayısı: " + 
                ((List<?>) heatmapData.get("features")).size());

            return ResponseEntity.ok(heatmapData);
        } catch (Exception e) {
            System.err.println("Heatmap verisi alınırken hata oluştu:");
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body(Map.of(
                            "type", "FeatureCollection",
                            "features", List.of(),
                            "error", e.getMessage()
                    ));
        }
    }
} 