package com.bjtg.shortly.url.service;

import com.bjtg.shortly.url.dto.UrlResponse;

public interface UrlService {
    UrlResponse getUrlByCode(String codeUrl);

    UrlResponse shortUrl(String url);
}
