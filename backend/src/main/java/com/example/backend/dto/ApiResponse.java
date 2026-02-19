package com.example.backend.dto;

// Generic response wrapper for all APIs
public class ApiResponse<T> {

    private String message; // One-line response message
    private T data; // Actual response data

    // Constructor
    public ApiResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    // Getter for message
    public String getMessage() {
        return message;
    }

    // Getter for data
    public T getData() {
        return data;
    }
}