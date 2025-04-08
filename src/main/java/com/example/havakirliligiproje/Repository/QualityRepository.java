package com.example.havakirliligiproje.Repository;

import com.example.havakirliligiproje.Entity.Quality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QualityRepository extends JpaRepository<Quality, String> {
    @Query
            (value = "SELECT * FROM air_quality_db WHERE location = :location AND timestamp >= NOW() - INTERVAL '24 hours'", nativeQuery = true)
    List<Quality> findLast24HoursByLocation(@Param("location") String location);
    List<Quality> findByLocationAndDateBetween(String location, String startDate, String endDate);
    List<Quality> findByLatitudeBetweenAndLongitudeBetween(
            double minLat, double maxLat,
            double minLon, double maxLon);

}
