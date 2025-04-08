package com.example.havakirliligiproje.Dto.Response;

import com.example.havakirliligiproje.Dto.Request.AnomalyRequest;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnomalyResponse {
    private String message;
    private String data;
    private List<AnomalyRequest> anomalyRequests;

    public static AnomalyResponse success(List<AnomalyRequest> anomalies) {
        return new AnomalyResponse("success",
                anomalies.size() + " adet anomali bulundu",
                anomalies);
    }
}
