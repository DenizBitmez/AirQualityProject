package com.example.havakirliligiproje.Service.Abstract;

import com.example.havakirliligiproje.Dto.Request.AnomalyRequest;
import com.example.havakirliligiproje.Dto.Response.HeatMapResponse;
import com.example.havakirliligiproje.Entity.Quality;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;


public interface QualityService {
    List<Quality> getAllAirQualityData();

    List<Quality> getAirQualityByLocation(String location);

    Quality saveAirQualityData(Quality airQuality);

    void addAirQualityData(Quality airQuality)throws JsonProcessingException;

    List<Quality> getPollutionByRegion(double latitude, double longitude);

    List<Quality> getLast24HoursDataByLocation(String location);

    List<Quality> getDataByDateRange(String location, String startDate, String endDate);

    List<Quality> getLast24HoursData(String location);

    List<AnomalyRequest> detectAnomalies(String location, String startDate, String endDate);

    List<AnomalyRequest> checkRegionalAnomalies(double latitude, double longitude, double radiusKm);

    Map<String, Object> getHeatmapData(
            Double minLat, Double maxLat,
            Double minLon, Double maxLon,
            Integer zoomLevel);
    double calculateGridSize(int zoomLevel);
    double normalizeValue(double value, double min, double max);

}
