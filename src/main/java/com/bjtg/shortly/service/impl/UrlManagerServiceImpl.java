package com.bjtg.shortly.service.impl;

import com.bjtg.shortly.dto.url.UrlResponse;
import com.bjtg.shortly.model.Url;
import com.bjtg.shortly.repository.UrlRepository;
import com.bjtg.shortly.service.UrlManagerService;
import com.bjtg.shortly.service.UrlShortService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlManagerServiceImpl implements UrlManagerService {
    private final UrlRepository urlRepository;
    private final UrlShortService urlShortService;

    private UrlManagerServiceImpl(UrlRepository urlRepository, UrlShortService urlShortService) {
        this.urlRepository = urlRepository;
        this.urlShortService = urlShortService;
    }

    private Url saveUrl(Url url) {
        return urlRepository.save(url);
    }

    private void updateUrl(Url url) {
        urlRepository.save(url);
    }

    private Optional<Url> getUrlByUrl(String url) {
        return urlRepository.findByOriginalUrl(url);
    }

    private void updateHitCount(Optional<Url> optionalUrl) {
        optionalUrl.ifPresent(url -> {
            url.setHitCount(url.getHitCount() + 1);
            updateUrl(url);
        });
    }

    private Url saveShortUrl(String urlRequest) {
        String shorCode = this.urlShortService.shortUrl();
        Url url = new Url();
        url.setOriginalUrl(urlRequest);
        url.setShortCode(shorCode);
        return saveUrl(url);
    }

    @Override
    public UrlResponse getUrlByCode(String codeUrl) {
        String longUrl = urlRepository.findByShortCode(codeUrl)
                .map(Url::getOriginalUrl)
                .orElseThrow(() -> new RuntimeException("URL not found"));

        return new UrlResponse(codeUrl, longUrl);
    }

    @Override
    public UrlResponse shortUrl(String urlRequest) {
        Optional<Url> optionalUrl = getUrlByUrl(urlRequest);

        return optionalUrl.map(existingUrl -> {
            updateHitCount(optionalUrl);
            return new UrlResponse(existingUrl.getShortCode(), urlRequest);
        }).orElseGet(() -> {
            Url savedUrl = saveShortUrl(urlRequest);
            return new UrlResponse(savedUrl.getShortCode(), savedUrl.getOriginalUrl());
        });
    }
}
