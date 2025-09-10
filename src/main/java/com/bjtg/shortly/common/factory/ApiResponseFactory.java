package com.bjtg.shortly.common.factory;

import com.bjtg.shortly.common.dto.ApiResponse;

public class ApiResponseFactory {
    public static <T> ApiResponse<T> succes(String message, T data) {
        return new ApiResponse<>("success", message, data, 200);
    }

    public static <T> ApiResponse<T> error(String message, T data, int code) {
        return new ApiResponse<>("error", message, data, code);
    }
}
