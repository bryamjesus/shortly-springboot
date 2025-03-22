package com.bjtg.shortly.service.impl;

import com.bjtg.shortly.model.Url;
import com.bjtg.shortly.repository.UrlRepository;
import com.bjtg.shortly.service.UrlManagerService;
import com.bjtg.shortly.service.UrlShortService;
import org.springframework.stereotype.Service;

import java.util.List;

class UrlRequest {
    private String url;
    private String shortURL;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShortURL() {
        return shortURL;
    }

    public void setShortURL(String shortURL) {
        this.shortURL = shortURL;
    }
}

@Service
public class UrlManagerServiceImpl implements UrlManagerService {

    private UrlRepository urlRepository;
    private UrlShortService urlShortService;

    private UrlManagerServiceImpl(UrlRepository urlRepository, UrlShortService urlShortService) {
        this.urlRepository = urlRepository;
        this.urlShortService = urlShortService;
    }

    @Override
    public void saveUrl(String originalUrl) { // cambiara a parametros especificos
        // patron use cases
        // estudiar mejor patron dto


        Url url = new Url();
        url.setOriginalUrl(originalUrl);

        url.setShortCode(this.urlShortService.shortUrl());
        //urlRepository.saveUrl(url);
    }

    @Override
    public List<Url> getUrl(String original) {
        return null;
                //this.urlRepository.findByUrl(original);
    }
}
