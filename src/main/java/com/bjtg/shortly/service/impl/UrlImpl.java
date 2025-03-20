package com.bjtg.shortly.service.impl;

import com.bjtg.shortly.dto.url.UrlRequest;
import com.bjtg.shortly.dto.url.UrlResponse;
import com.bjtg.shortly.model.Url;
import com.bjtg.shortly.repository.UrlRepository;
import com.bjtg.shortly.service.UrlService;
import org.springframework.stereotype.Repository;

@Repository
public class UrlImpl implements UrlService {

    private final UrlRepository urlRepository;

    public UrlImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public UrlResponse shorUrl(UrlRequest urlRequest) {
        return null;
    }

}
