package com.bjtg.shortly.service;

import com.bjtg.shortly.url.dto.UrlResponse;

public interface UrlManagerService {
    UrlResponse getUrlByCode(String codeUrl);

    UrlResponse shortUrl(String url);
}
