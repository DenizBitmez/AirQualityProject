package com.example.havakirliligiproje.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QualityRequest {
    @NotBlank
    private String location;
    @Positive
    private Double pm25;
    @Positive
    private Double pm10;
    @Positive
    private double no2;
    @Positive
    private double so2;
    @Positive
    private double o3;
    @Positive
    private double latitude;
    @Positive
    private double longitude;
}
