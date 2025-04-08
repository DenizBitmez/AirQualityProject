package com.example.havakirliligiproje.Dto.Request;

import com.example.havakirliligiproje.Entity.Quality;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnomalyRequest {
    private String location;
    private Double pm25;
    private Double pm10;
    private Instant timestamp;
    private String anomalyType;
    private Quality qualityData;

}
