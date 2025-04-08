package com.example.havakirliligiproje.Service.Concrete;

import com.example.havakirliligiproje.Entity.Quality;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class zAnomalyService {
    public boolean isZAnomaly(Quality currentData, List<Quality> qualities) {
        List<Double> values=qualities.stream()
                .map(Quality::getPm25)
                .collect(Collectors.toList());

        double mean=calculateMean(values);
        double stdDev = calculateStdDev(values, mean);
        double zScore = Math.abs((currentData.getPm25() - mean) / stdDev);

        return zScore > 3;
    }

    private double calculateMean(List<Double> values) {
       return values.stream().mapToDouble(Double::doubleValue).average().orElse(0);
    }

    private double calculateStdDev(List<Double> values, double mean) {
        return Math.sqrt(values.stream()
                .mapToDouble(v -> Math.pow(v - mean, 2))
                .average().orElse(0));
    }
}
