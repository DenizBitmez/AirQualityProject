package com.airquality.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeatmapRequest {
    @NotNull(message = "minLat boş olamaz")
    @Min(value = -90, message = "minLat -90'dan küçük olamaz")
    @Max(value = 90, message = "minLat 90'dan büyük olamaz")
    private Double minLat;

    @NotNull(message = "maxLat boş olamaz")
    @Min(value = -90, message = "maxLat -90'dan küçük olamaz")
    @Max(value = 90, message = "maxLat 90'dan büyük olamaz")
    private Double maxLat;

    @NotNull(message = "minLon boş olamaz")
    @Min(value = -180, message = "minLon -180'den küçük olamaz")
    @Max(value = 180, message = "minLon 180'den büyük olamaz")
    private Double minLon;

    @NotNull(message = "maxLon boş olamaz")
    @Min(value = -180, message = "maxLon -180'den küçük olamaz")
    @Max(value = 180, message = "maxLon 180'den büyük olamaz")
    private Double maxLon;

    @NotNull(message = "zoomLevel boş olamaz")
    @Min(value = 0, message = "zoomLevel 0'dan küçük olamaz")
    @Max(value = 22, message = "zoomLevel 22'den büyük olamaz")
    private Integer zoomLevel;
} 