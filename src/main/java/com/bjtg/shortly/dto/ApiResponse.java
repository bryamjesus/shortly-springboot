package com.bjtg.shortly.dto;

public class ApiResponse<T> {
    private String status;
    private String message;
    private T data;
    private int code; // Nuevo campo para el código HTTP

    public ApiResponse() {
    }

    public ApiResponse(String status, String message, T data, int code) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
