package com.example.backend.exception;

import com.example.backend.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // Handles exceptions globally
public class GlobalExceptionHandler {

    // Handle conflict exceptions → return 409 CONFLICT
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Void>> handleRuntimeException(RuntimeException ex) {

        return ResponseEntity
                .status(409)
                .body(new ApiResponse<>(ex.getMessage(), null));
    }

    // Handle generic exceptions → return 500 INTERNAL SERVER ERROR
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGenericException(Exception ex) {

        return ResponseEntity
                .status(500)
                .body(new ApiResponse<>("Something went wrong", null));
    }
}