package com.airquality.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeatmapResponse {
    private Double lat;
    private Double lon;
    private Double avgPm25;
} 