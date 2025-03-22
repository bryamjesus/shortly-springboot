package com.bjtg.shortly.service.impl;

import com.bjtg.shortly.dto.url.UrlResponse;
import com.bjtg.shortly.model.Url;
import com.bjtg.shortly.repository.UrlRepository;
import com.bjtg.shortly.service.UrlManagerService;
import com.bjtg.shortly.service.UrlShortService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UrlManagerServiceImpl implements UrlManagerService {

    private final UrlRepository urlRepository;
    private final UrlShortService urlShortService;

    private UrlManagerServiceImpl(UrlRepository urlRepository, UrlShortService urlShortService) {
        this.urlRepository = urlRepository;
        this.urlShortService = urlShortService;
    }

    @Override
    public UrlResponse getUrlByCode(String codeUrl) {
        String longUrl = urlRepository.findByShortCode(codeUrl)
                .map(Url::getOriginalUrl)
                .orElseThrow(() -> new RuntimeException("URL not found"));

        return new UrlResponse(codeUrl, longUrl);
    }

}
