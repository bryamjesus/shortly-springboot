package com.bjtg.shortly.common.exception;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bjtg.shortly.common.dto.ApiResponse;
import com.bjtg.shortly.common.factory.ApiResponseFactory;
import com.bjtg.shortly.url.exception.UrlNotFoundException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleUrlNotFound(
            UrlNotFoundException ex,
            HttpServletRequest request) {

        LOGGER.warn("URL not found: {} - {}", request.getRequestURI(), ex.getMessage());

        ApiResponse<Object> response = ApiResponseFactory.errorNotFound(
                ex.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleConstraintViolation(
            ConstraintViolationException ex,
            HttpServletRequest request) {

        String errors = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));

        LOGGER.warn("Constraint violation on {}: {}", request.getRequestURI(), errors);

        ApiResponse<Object> response = ApiResponseFactory.errorBadRequest(
                errors,
                request.getRequestURI());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationErrors(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        String errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        LOGGER.warn("Validation failed on {}: {}", request.getRequestURI(), errors);

        ApiResponse<Object> response = ApiResponseFactory.errorBadRequest(
                errors,
                request.getRequestURI());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGenericException(
            Exception ex,
            HttpServletRequest request) {

        LOGGER.error("Unexpected error on {}: ", request.getRequestURI(), ex);

        ApiResponse<Object> response = ApiResponseFactory.errorInternalServer(
                "An unexpected error occurred. Please contact support.",
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
