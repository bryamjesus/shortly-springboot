package com.bjtg.shortly.common.factory;

import org.springframework.http.HttpStatus;

import com.bjtg.shortly.common.dto.ApiResponse;

public class ApiResponseFactory {
    private ApiResponseFactory() {
        throw new UnsupportedOperationException("Utility class");
    }

    // ============ MÉTODOS BASE ============
    private static <T> ApiResponse<T> success(HttpStatus status, String message, T data, String path) {
        return new ApiResponse<>("success", status.value(), message, data, path);
    }

    private static <T> ApiResponse<T> error(HttpStatus status, String message, T data, String path) {
        return new ApiResponse<>("error", status.value(), message, data, path);
    }

    // ============ SUCCESS RESPONSES (2XX) ============

    public static <T> ApiResponse<T> successOk(String message, T data, String path) {
        return success(HttpStatus.OK, message, data, path);
    }

    /**
     * 201 CREATED
     */
    public static <T> ApiResponse<T> successCreated(String message, T data, String path) {
        return success(HttpStatus.CREATED, message, data, path);
    }

    /**
     * 204 NO CONTENT
     */
    public static <T> ApiResponse<T> successNoContent(String message, String path) {
        return success(HttpStatus.NO_CONTENT, message, null, path);
    }

    // ============ CLIENT ERROR RESPONSES (4XX) ============

    /**
     * 400 BAD REQUEST
     */
    public static <T> ApiResponse<T> errorBadRequest(String message, String path) {
        return error(HttpStatus.BAD_REQUEST, message, null, path);
    }

    /**
     * 401 UNAUTHORIZED
     */
    public static <T> ApiResponse<T> errorUnauthorized(String message, String path) {
        return error(HttpStatus.UNAUTHORIZED, message, null, path);
    }

    /**
     * 403 FORBIDDEN
     */
    public static <T> ApiResponse<T> errorForbidden(String message, String path) {
        return error(HttpStatus.FORBIDDEN, message, null, path);
    }

    /**
     * 404 NOT FOUND
     */
    public static <T> ApiResponse<T> errorNotFound(String message, String path) {
        return error(HttpStatus.NOT_FOUND, message, null, path);
    }

    /**
     * 409 CONFLICT
     */
    public static <T> ApiResponse<T> errorConflict(String message, String path) {
        return error(HttpStatus.CONFLICT, message, null, path);
    }

    /**
     * 422 UNPROCESSABLE ENTITY
     */
    public static <T> ApiResponse<T> errorUnprocessableEntity(String message, String path) {
        return error(HttpStatus.UNPROCESSABLE_ENTITY, message, null, path);
    }

    // ============ SERVER ERROR RESPONSES (5XX) ============

    /**
     * 500 INTERNAL SERVER ERROR
     */
    public static <T> ApiResponse<T> errorInternalServer(String message, String path) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, message, null, path);
    }

    /**
     * 503 SERVICE UNAVAILABLE
     */
    public static <T> ApiResponse<T> errorServiceUnavailable(String message, String path) {
        return error(HttpStatus.SERVICE_UNAVAILABLE, message, null, path);
    }
}