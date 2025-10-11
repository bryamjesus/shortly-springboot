package com.bjtg.shortly.common.dto;

import java.time.Instant;

public record ApiResponse<T>(
        String timestamp,
        String status,
        int code,
        String message,
        T data,
        String path) {
    public ApiResponse(String status, int code, String message, T data, String path) {
        this(Instant.now().toString(), status, code, message, data, path);
    }
}
