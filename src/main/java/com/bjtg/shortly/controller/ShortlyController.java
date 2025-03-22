package com.bjtg.shortly.controller;

import com.bjtg.shortly.dto.url.ShortUrlRequest;
import com.bjtg.shortly.service.UrlManagerService;
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
    public ResponseEntity<String> getUrlByCodeUrl(@PathVariable(value = "codeUrl") String codeUrl) {
        urlManagerService.getUrlByCode(codeUrl);
        return ResponseEntity.ok("Hello World " + codeUrl);
    }

    // http://localhost:8080/shortly
    @PostMapping()
    public ResponseEntity<String> shortUrl(@Validated @RequestBody ShortUrlRequest shortUrlRequest) {
        return ResponseEntity.ok("Hello World " + shortUrlRequest.getUrl());
    }

}

// patron use cases
// estudiar mejor patron dto
