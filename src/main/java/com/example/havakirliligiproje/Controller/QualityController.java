package com.example.havakirliligiproje.Controller;

import com.example.havakirliligiproje.Api.ApiResponse;
import com.example.havakirliligiproje.Dto.Request.QualityRequest;
import com.example.havakirliligiproje.Dto.Response.QualityResponse;
import com.example.havakirliligiproje.Entity.Quality;
import com.example.havakirliligiproje.Service.Concrete.QualityServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/quality")
public class QualityController {
    private final QualityServiceImpl airQualityService;

    public QualityController(QualityServiceImpl airQualityService) {
        this.airQualityService = airQualityService;
    }

    @GetMapping("/all")
    public List<Quality> getAllAirQualityData() {
        return airQualityService.getAllAirQualityData();
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<ApiResponse> getAirQualityByLocation(@RequestParam String location) {
        Quality quality = airQualityService.getAirQualityByLocation(location);
        if (quality == null) {
            ApiResponse response = new ApiResponse("error", "Belirtilen konum için hava kalitesi verisi bulunamadı.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        ApiResponse response = new ApiResponse("success", "Hava kalitesi verisi başarıyla getirildi.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    public Quality saveAirQualityData(@RequestBody Quality Quality) {
        return airQualityService.saveAirQualityData(Quality);
    }

    @GetMapping("/anomaly/{location}")
    public ResponseEntity<ApiResponse> checkAnomalies(
            @RequestParam String location,
            @RequestParam String startDate,
            @RequestParam String endDate) {

        List<Quality> anomalies = airQualityService.findAnomalies(location, startDate, endDate);

        if (anomalies.isEmpty()) {
            ApiResponse response = new ApiResponse("error", "Belirtilen tarihler arasında anomali tespit edilmedi.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        ApiResponse response = new ApiResponse("success", "Anomaliler başarıyla getirildi.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<QualityResponse> addAirQualityData(@RequestBody QualityRequest airQuality) {
        Quality quality = new Quality();
        quality.setLocation(airQuality.getLocation());
        quality.setPm25(airQuality.getPm25());
        quality.setPm25(airQuality.getPm10());
        quality.setPm25(airQuality.getO3());
        quality.setPm25(airQuality.getSo2());
        quality.setPm25(airQuality.getNo2());

        Quality savedData = airQualityService.saveAirQualityData(quality);

        QualityResponse response = new QualityResponse(
                "success",
                "Veri başarıyla kaydedildi.",
                savedData
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/region/pollution")
    public ResponseEntity<ApiResponse> getPollutionByRegion(@RequestParam double latitude, @RequestParam double longitude) {
        List<Quality> regionData = airQualityService.getPollutionByRegion(latitude, longitude);
        if (regionData.isEmpty()) {
            ApiResponse response = new ApiResponse("error", "Bu bölgedeki hava kalitesi verisi bulunamadı.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        ApiResponse response = new ApiResponse("success", "Bölgedeki kirlilik yoğunluğu başarıyla getirildi.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/last-24-hours/{location}")
    public ResponseEntity<ApiResponse> getLast24HoursDataByLocation(@PathVariable String location) {
        List<Quality> data = airQualityService.getLast24HoursDataByLocation(location);

        if (data.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("error", "Son 24 saatte veri bulunamadı"));
        }

        return ResponseEntity.ok()
                .body(new ApiResponse("success", "Son 24 saat verileri"));
    }
}
