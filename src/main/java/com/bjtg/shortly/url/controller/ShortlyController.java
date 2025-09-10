package com.bjtg.shortly.url.controller;

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
import com.bjtg.shortly.service.UrlManagerService;
import com.bjtg.shortly.url.dto.ShortUrlRequest;
import com.bjtg.shortly.url.dto.UrlResponse;

import jakarta.validation.constraints.Pattern;

@Validated
@RestController
@RequestMapping("shortly")
public class ShortlyController {
    private final UrlManagerService urlManagerService;

    public ShortlyController(UrlManagerService urlManagerService) {
        this.urlManagerService = urlManagerService;
    }

    // http://localhost:8080/shortly/{codeUrl}
    @GetMapping("/{codeUrl}")
    public ResponseEntity<ApiResponse<UrlResponse>> getUrlByCodeUrl(
            @PathVariable(value = "codeUrl") @Pattern(regexp = "^[A-Za-z0-9]{8}$", message = "El código debe tener 8 caracteres y contener solo letras mayúsculas y números") String codeUrl) {
        UrlResponse urlResponse = urlManagerService.getUrlByCode(codeUrl);
        return ResponseEntity.ok(ApiResponseFactory.succes("Url retrieved successfully", urlResponse));
    }

    // http://localhost:8080/shortly
    @PostMapping()
    public ResponseEntity<ApiResponse<UrlResponse>> shortUrl(@Validated @RequestBody ShortUrlRequest shortUrlRequest) {
        UrlResponse urlResponse = urlManagerService.shortUrl(shortUrlRequest.getUrl());
        return ResponseEntity.ok(ApiResponseFactory.succes("Short URL generate successfully", urlResponse));
    }
}

// patron use cases
// estudiar mejor patron dto
