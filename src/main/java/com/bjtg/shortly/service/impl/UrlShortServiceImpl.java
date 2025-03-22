package com.bjtg.shortly.service.impl;

import com.bjtg.shortly.service.UrlShortService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UrlShortServiceImpl implements UrlShortService {

    public String shortUrl() {
        return getSaltString();
    }

    private String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }

}
