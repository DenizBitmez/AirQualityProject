package com.example.havakirliligiproje.Repository;

import com.example.havakirliligiproje.Entity.Quality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QualityRepository extends JpaRepository<Quality, String> {
    Quality findByLocation(String location);
    List<Quality> findByLocationAndDateBetween(String location, String startDate, String endDate);
    List<Quality> findByLatitudeBetweenAndLongitudeBetween(
            double minLat, double maxLat,
            double minLon, double maxLon);
}
