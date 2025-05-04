package com.airquality.service;

import java.util.Map;

public interface QualityService {
    Map<String, Object> getHeatmapData(
            Double minLat, Double maxLat,
            Double minLon, Double maxLon,
            Integer zoomLevel);
} 