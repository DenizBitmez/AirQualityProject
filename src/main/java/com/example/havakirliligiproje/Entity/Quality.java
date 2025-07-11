package com.example.havakirliligiproje.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "air_quality")
public class Quality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double pm25;
    private Double pm10;
    private Double no2;
    private Double so2;
    private Double o3;
    private String location;
    @CreationTimestamp
    @Column(name = "timestamp", columnDefinition = "TIMESTAMPTZ")
    private Instant timestamp;
    @Column(name = "date")
    private LocalDate date;
    private Double latitude;
    private Double longitude;
}
