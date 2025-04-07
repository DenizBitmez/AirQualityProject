package com.example.havakirliligiproje.Api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse {
    private String status;
    private String message;

    public  ApiResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
