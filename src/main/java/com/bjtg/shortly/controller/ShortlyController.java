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
    public ResponseEntity<String> getUrlWithCode(@PathVariable(value = "codeUrl") String codeUrl) {
        return ResponseEntity.ok("Hello World " + codeUrl);
    }

    // http://localhost:8080/shortly
    @PostMapping()
    public ResponseEntity<String> shortUrl(@RequestBody @Validated ShortUrlRequest shortUrlRequest) {
        return ResponseEntity.ok("Hello World " + shortUrlRequest.getUrl());
    }

}
