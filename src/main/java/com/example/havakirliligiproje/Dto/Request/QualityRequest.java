package com.example.havakirliligiproje.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @Positive
    private Double pm25;
    @NotNull
    @Positive
    private Double pm10;
    @NotNull
    @Positive
    private double no2;
    @NotNull
    @Positive
    private double so2;
    @NotNull
    @Positive
    private double o3;
    private double latitude;
    private double longitude;
}
