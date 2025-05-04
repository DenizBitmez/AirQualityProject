package com.airquality.repository;

import com.airquality.entity.AirQuality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirQualityRepository extends JpaRepository<AirQuality, Long> {
    
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
} 