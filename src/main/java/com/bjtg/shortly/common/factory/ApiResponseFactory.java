package com.bjtg.shortly.common.factory;

import org.springframework.http.HttpStatus;

import com.bjtg.shortly.common.dto.ApiResponse;

public class ApiResponseFactory {
    public static <T> ApiResponse<T> success(String message, T data, HttpStatus status) {
        return new ApiResponse<>("success", message, data, status.value());
    }

    public static <T> ApiResponse<T> successOk(String message, T data) {
        return ApiResponseFactory.success(message, data, HttpStatus.OK);
    }

    public static <T> ApiResponse<T> createCreated(String message, T data) {
        return ApiResponseFactory.success(message, data, HttpStatus.CREATED);
    }

    public static <T> ApiResponse<T> error(String message, T data, int code) {
        return new ApiResponse<>("error", message, data, code);
    }

    public static <T> ApiResponse<T> error(String message, int code) {
        return ApiResponseFactory.error(message, null, code);
    }
}
