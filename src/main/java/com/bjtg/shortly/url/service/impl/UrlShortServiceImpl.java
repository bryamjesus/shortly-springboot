package com.bjtg.shortly.url.service.impl;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

import com.bjtg.shortly.url.service.UrlShortService;

@Service
public class UrlShortServiceImpl implements UrlShortService {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final int CODE_LENGTH = 8;
    private final SecureRandom random = new SecureRandom();

    public String shortUrl() {
        return generateCode();
    }

    private String generateCode() {
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return code.toString();
    }
}
