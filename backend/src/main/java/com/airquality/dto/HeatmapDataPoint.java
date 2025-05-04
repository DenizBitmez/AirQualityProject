package com.airquality.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeatmapDataPoint {
    private Double lat;
    private Double lon;
    private Double avgPm25;
    private Double intensity; // normalize edilmiş PM2.5 değeri (0-1 arası)
} 