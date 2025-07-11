package com.example.havakirliligiproje.Dto.Response;

import com.example.havakirliligiproje.Entity.Quality;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QualityResponse {
    private String status;
    private String message;
    private Quality data;

}

