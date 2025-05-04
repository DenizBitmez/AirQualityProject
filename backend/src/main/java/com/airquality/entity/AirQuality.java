package com.airquality.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "air_quality")
@NoArgsConstructor
@AllArgsConstructor
public class AirQuality {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "pm25")
    private Double pm25;

    // Opsiyonel: Diğer sütunlar varsa ekleyin
} 