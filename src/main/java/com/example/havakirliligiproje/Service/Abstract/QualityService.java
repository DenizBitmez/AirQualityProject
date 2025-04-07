package com.example.havakirliligiproje.Service.Abstract;

import com.example.havakirliligiproje.Entity.Quality;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;


public interface QualityService {
    List<Quality> getAllAirQualityData();

    Quality getAirQualityByLocation(String location);

    Quality saveAirQualityData(Quality airQuality);

    List<Quality> findAnomalies(String location, String startDate, String endDate);

    void addAirQualityData(Quality airQuality)throws JsonProcessingException;

    List<Quality> getPollutionByRegion(double latitude, double longitude);

    List<Quality> getLast24HoursDataByLocation(String location);

}
