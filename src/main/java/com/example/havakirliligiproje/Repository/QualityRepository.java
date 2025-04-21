package com.example.havakirliligiproje.Repository;

import com.example.havakirliligiproje.Entity.Quality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface QualityRepository extends JpaRepository<Quality, String> {
    @Query
            (value = "SELECT * FROM quality WHERE location = :location AND timestamp >= NOW() - INTERVAL '24 hours'", nativeQuery = true)
    List<Quality> findLast24HoursByLocation(@Param("location") String location);
    List<Quality> findByLatitudeBetweenAndLongitudeBetween(
            double minLat, double maxLat,
            double minLon, double maxLon);

    List<Quality> findByLocationAndTimestampBetween(
            String location,
            Instant start,
            Instant end
    );

    @Query(value = """
        SELECT * FROM quality WHERE (POWER(latitude - :latitude, 2) + POWER(longitude - :longitude, 2)) <= POWER(:radius, 2)
        AND timestamp >= :cutoffTime
        """, nativeQuery = true)
    List<Quality> findRegionalLast24Hours(
            @Param("latitude") double latitude,
            @Param("longitude") double longitude,
            @Param("radius") double radius,
            @Param("cutoffTime") Instant cutoffTime);

    List<Quality> findByLocation(String location);
}
