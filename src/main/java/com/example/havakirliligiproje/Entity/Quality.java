package com.example.havakirliligiproje.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
public class Quality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double pm25;
    private double pm10;
    private double no2;
    private double so2;
    private double o3;
    private String location;
    @Column(name = "timestamp", columnDefinition = "TIMESTAMPTZ")
    private Instant timestamp;
    @Column(name = "date")
    private String date;
    private double latitude;
    private double longitude;
}
