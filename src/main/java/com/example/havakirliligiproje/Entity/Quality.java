package com.example.havakirliligiproje.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @NotNull
    @Positive
    private double pm25;
    @NotNull @Positive
    private double pm10;
    @NotNull @Positive
    private double no2;
    @NotNull @Positive
    private double so2;
    @NotNull @Positive
    private double o3;
    private String location;
    @Column(name = "timestamp", columnDefinition = "TIMESTAMPTZ")
    private Instant timestamp;
    @Column(name = "date")
    private String date;
    private double latitude;
    private double longitude;
}
