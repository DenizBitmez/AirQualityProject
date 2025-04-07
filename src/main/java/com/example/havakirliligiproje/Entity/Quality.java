package com.example.havakirliligiproje.Entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class Quality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private double pm25;
    private double pm10;
    private double no2;
    private double so2;
    private double o3;
    private String location;
    @Column(name = "timestamp", columnDefinition = "TIMESTAMPTZ")
    private Instant timestamp;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPm25() {
        return pm25;
    }

    public void setPm25(double pm25) {
        this.pm25 = pm25;
    }

    public double getPm10() {
        return pm10;
    }

    public void setPm10(double pm10) {
        this.pm10 = pm10;
    }

    public double getNo2() {
        return no2;
    }

    public void setNo2(double no2) {
        this.no2 = no2;
    }

    public double getSo2() {
        return so2;
    }

    public void setSo2(double so2) {
        this.so2 = so2;
    }

    public double getO3() {
        return o3;
    }

    public void setO3(double o3) {
        this.o3 = o3;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

}
