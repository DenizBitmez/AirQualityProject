package com.example.havakirliligiproje.Dto.Response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HeatMapResponse {
    private Double lat;
    private Double lon;
    private Double avgPm25;
}
