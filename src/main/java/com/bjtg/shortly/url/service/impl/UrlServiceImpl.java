package com.bjtg.shortly.url.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bjtg.shortly.url.dto.UrlResponse;
import com.bjtg.shortly.url.exception.UrlNotFoundException;
import com.bjtg.shortly.url.model.Url;
import com.bjtg.shortly.url.repository.UrlRepository;
import com.bjtg.shortly.url.service.ShortCodeGenerator;
import com.bjtg.shortly.url.service.UrlService;

@Service
public class UrlServiceImpl implements UrlService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UrlServiceImpl.class);

    private final UrlRepository urlRepository;
    private final ShortCodeGenerator shortCodeGenerator;

    private UrlServiceImpl(UrlRepository urlRepository, ShortCodeGenerator shortCodeGenerator) {
        this.urlRepository = urlRepository;
        this.shortCodeGenerator = shortCodeGenerator;
    }

    private Url saveUrl(Url url) {
        LOGGER.debug("Saving new Url entity: originalUrl={}, shortCode={}", url.getOriginalUrl(), url.getShortCode());
        return urlRepository.save(url);
    }

    private void updateUrl(Url url) {
        LOGGER.debug("Updating Url entity: id={}, shortCode={}, hitCount={}",
                url.getId(), url.getShortCode(), url.getHitCount());
        urlRepository.save(url);
    }

    private Optional<Url> findByOriginalUrl(String url) {
        return urlRepository.findByOriginalUrl(url);
    }

    private void updateHitCount(Url url) {
        LOGGER.debug("Incrementing hit count for shortCode={} (currentHitCount={})",
                url.getShortCode(), url.getHitCount());
        url.increaseHitCount();
        updateUrl(url);
    }

    private String generateShortCode() {
        String shortCode = this.shortCodeGenerator.generateShortCode();
        Optional<Url> url = urlRepository.findByShortCode(shortCode);

        LOGGER.debug("Generated shortCode={} alreadyExists={}", shortCode, url.isPresent());

        if (url.isPresent()) {
            LOGGER.warn("ShortCode={} already exists, regenerating...", shortCode);
            return generateShortCode();
        }
        return shortCode;
    }

    private Url saveShortUrl(String urlRequest) {
        String shortCode = generateShortCode();
        LOGGER.info("Generated new shortCode={} for url={}", shortCode, urlRequest);
        Url url = new Url(shortCode, urlRequest);
        return saveUrl(url);
    }

    @Override
    public UrlResponse getUrlByCode(String codeUrl) {
        String longUrl = urlRepository.findByShortCode(codeUrl)
                .map(Url::getOriginalUrl)
                .orElseThrow(() -> {
                    LOGGER.error("ShortCode={} not found", codeUrl);
                    return new UrlNotFoundException();
                });

        LOGGER.info("Retrieved originalUrl={} for shortCode={}", longUrl, codeUrl);

        return UrlResponse
                .builder()
                .shortCode(codeUrl)
                .originalUrl(longUrl)
                .build();
    }

    @Override
    public UrlResponse shortUrl(String urlRequest) {
        LOGGER.debug("Request to shorten url={}", urlRequest);

        Optional<Url> optionalUrl = findByOriginalUrl(urlRequest);

        return optionalUrl.map(url -> {
            updateHitCount(url);
            LOGGER.info("Url already existed: shortCode={}, originalUrl={}, newHitCount={}",
                    url.getShortCode(), url.getOriginalUrl(), url.getHitCount());

            return UrlResponse
                    .builder()
                    .shortCode(url.getShortCode())
                    .originalUrl(urlRequest)
                    .build();
        }).orElseGet(() -> {
            Url savedUrl = saveShortUrl(urlRequest);
            LOGGER.info("Created new Url: shortCode={}, originalUrl={}",
                    savedUrl.getShortCode(), savedUrl.getOriginalUrl());

            return UrlResponse
                    .builder()
                    .shortCode(savedUrl.getShortCode())
                    .originalUrl(savedUrl.getOriginalUrl())
                    .build();
        });
    }
}
