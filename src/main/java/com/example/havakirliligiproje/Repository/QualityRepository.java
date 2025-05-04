package com.example.havakirliligiproje.Repository;

import com.example.havakirliligiproje.Entity.Quality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface QualityRepository extends JpaRepository<Quality, String> {
    @Query("SELECT q FROM Quality q WHERE q.timestamp >= :cutoffTime")
    List<Quality> findLast24Hours(@Param("cutoffTime") Instant cutoffTime);
    List<Quality> findByLatitudeBetweenAndLongitudeBetween(
            double minLat, double maxLat,
            double minLon, double maxLon);

    List<Quality> findByLocationAndTimestampBetween(
            String location,
            Instant start,
            Instant end
    );

    @Query(value = """
        SELECT * FROM air_quality WHERE (POWER(latitude - :latitude, 2) + POWER(longitude - :longitude, 2)) <= POWER(:radiusInDegrees, 2)
        AND timestamp >= :cutoffTime
        """, nativeQuery = true)
    List<Quality> findRegionalLast24Hours(
            @Param("latitude") double latitude,
            @Param("longitude") double longitude,
            @Param("radiusInDegrees") double radiusInDegrees,
            @Param("cutoffTime") Instant cutoffTime);

    List<Quality> findByLocation(String location);

    @Query(value = """
            WITH grid_points AS (
                SELECT 
                    longitude,
                    latitude,
                    pm25,
                    ROUND(longitude / :gridSize) * :gridSize as grid_lon,
                    ROUND(latitude / :gridSize) * :gridSize as grid_lat
                FROM air_quality
                WHERE latitude BETWEEN :minLat AND :maxLat
                AND longitude BETWEEN :minLon AND :maxLon
            )
            SELECT 
                grid_lon as lon,
                grid_lat as lat,
                AVG(pm25) as avg_pm25
            FROM grid_points
            GROUP BY grid_lon, grid_lat
            """, nativeQuery = true)
    List<Object[]> findHeatmapData(
            @Param("minLat") Double minLat,
            @Param("maxLat") Double maxLat,
            @Param("minLon") Double minLon,
            @Param("maxLon") Double maxLon,
            @Param("gridSize") Double gridSize);

    List<Quality> findLast24HoursByLocation(String location);
}
