package com.bjtg.shortly.service.impl;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.bjtg.shortly.service.UrlShortService;

@Service
public class UrlShortServiceImpl implements UrlShortService {
    public String shortUrl() {
        return getSaltString();
    }

    private String getSaltString() {
        String SALTCHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }
}
