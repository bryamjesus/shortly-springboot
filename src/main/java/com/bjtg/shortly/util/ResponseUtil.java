package com.bjtg.shortly.util;

import com.bjtg.shortly.dto.ApiResponse;

public class ResponseUtil {

    public static <T> ApiResponse<T> succes(String message, T data) {
        return new ApiResponse<>("success", message, data);
    }

    public static <T> ApiResponse<T> error(String message, T data) {
        return new ApiResponse<>("error", message, data);
    }
}
