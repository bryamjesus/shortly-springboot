package com.bjtg.shortly.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortlyController {

    @GetMapping
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Hello World");
    }
}
