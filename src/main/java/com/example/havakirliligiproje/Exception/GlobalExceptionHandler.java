package com.example.havakirliligiproje.Exception;

import com.example.havakirliligiproje.Dto.Response.ErrorResponse;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<com.example.havakirliligiproje.Dto.Response.ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        com.example.havakirliligiproje.Dto.Response.ErrorResponse response = new com.example.havakirliligiproje.Dto.Response.ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<com.example.havakirliligiproje.Dto.Response.ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        com.example.havakirliligiproje.Dto.Response.ErrorResponse response = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                request.getDescription(false)
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
