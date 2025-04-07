package com.example.havakirliligiproje.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
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
    @PositiveOrZero
    private Double pm25;
    @PositiveOrZero
    private Double pm10;
    @PositiveOrZero
    private double no2;
    @PositiveOrZero
    private double so2;
    @PositiveOrZero
    private double o3;
}
