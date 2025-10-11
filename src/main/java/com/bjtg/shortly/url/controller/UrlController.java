package com.bjtg.shortly.url.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bjtg.shortly.common.dto.ApiResponse;
import com.bjtg.shortly.common.factory.ApiResponseFactory;
import com.bjtg.shortly.url.dto.UrlRequest;
import com.bjtg.shortly.url.dto.UrlResponse;
import com.bjtg.shortly.url.service.UrlService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping("/urls")
@Validated
public class UrlController {
    private final UrlService urlManagerService;

    public UrlController(UrlService urlManagerService) {
        this.urlManagerService = urlManagerService;
    }

    /**
     * Obtiene una URL por su código corto
     * 
     * @param codeUrl código alfanumérico de 8 caracteres
     * @param request para obtener la URI del request
     * @return ResponseEntity con la URL encontrada
     */
    @GetMapping("/{codeUrl}")
    public ResponseEntity<ApiResponse<UrlResponse>> getUrlByCodeUrl(
            @PathVariable @Pattern(regexp = "^[A-Za-z0-9]{8}$", message = "El código debe tener 8 caracteres alfanuméricos") String codeUrl,
            HttpServletRequest request) {
        UrlResponse urlResponse = urlManagerService.getUrlByCode(codeUrl);

        ApiResponse<UrlResponse> response = ApiResponseFactory.successOk(
                "URL retrieved successfully",
                urlResponse,
                request.getRequestURI());

        return ResponseEntity.ok(response);
    }

    /**
     * Crea una URL corta a partir de una URL larga
     * 
     * @param shortUrlRequest objeto con la URL a acortar
     * @param request         para obtener la URI del request
     * @return ResponseEntity con la URL corta generada y header Location
     */
    @PostMapping
    public ResponseEntity<ApiResponse<UrlResponse>> shortenUrl(
            @Valid @RequestBody UrlRequest shortUrlRequest,
            HttpServletRequest request) {
        UrlResponse urlResponse = urlManagerService.shortUrl(shortUrlRequest.getUrl());

        ApiResponse<UrlResponse> response = ApiResponseFactory.successCreated(
                "Short URL generated successfully",
                urlResponse,
                request.getRequestURI());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", "/shortly/" + urlResponse.getShortCode())
                .body(response);
    }
}
