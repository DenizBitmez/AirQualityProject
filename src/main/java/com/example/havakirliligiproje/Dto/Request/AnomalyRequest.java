package com.example.havakirliligiproje.Dto.Request;

import com.example.havakirliligiproje.Entity.Quality;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @NotNull
    @Positive
    private Double pm25;
    @NotNull
    @Positive
    private Double pm10;
    private Instant timestamp;
    private String anomalyType;
    private Quality qualityData;

}
