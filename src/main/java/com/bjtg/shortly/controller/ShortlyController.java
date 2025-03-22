package com.bjtg.shortly.controller;

import com.bjtg.shortly.dto.ApiResponse;
import com.bjtg.shortly.dto.url.ShortUrlRequest;
import com.bjtg.shortly.dto.url.UrlResponse;
import com.bjtg.shortly.service.UrlManagerService;
import com.bjtg.shortly.util.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("shortly")
public class ShortlyController {

    private final UrlManagerService urlManagerService;

    public ShortlyController(UrlManagerService urlManagerService) {
        this.urlManagerService = urlManagerService;
    }

    // http://localhost:8080/shortly/{codeUrl}
    @GetMapping("/{codeUrl}")
    public ResponseEntity<ApiResponse<UrlResponse>> getUrlByCodeUrl(@PathVariable(value = "codeUrl") String codeUrl) {
        UrlResponse urlResponse = urlManagerService.getUrlByCode(codeUrl);
        return ResponseEntity.ok(ResponseUtil.succes("Code", urlResponse));
    }

    // http://localhost:8080/shortly
    @PostMapping()
    public ResponseEntity<ApiResponse<UrlResponse>> shortUrl(@Validated @RequestBody ShortUrlRequest shortUrlRequest) {
        UrlResponse urlResponse = urlManagerService.shortUrl(shortUrlRequest.getUrl());
        return ResponseEntity.ok(ResponseUtil.succes("Code 2", urlResponse));
    }

}

// patron use cases
// estudiar mejor patron dto
