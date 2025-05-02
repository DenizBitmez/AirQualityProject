package com.example.havakirliligiproje.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HeatMapRequest {
    private Double minLat;
    private Double maxLat;
    private Double minLon;
    private Double maxLon;
    private Double gridSize;
    private Integer zoomLevel;
}
