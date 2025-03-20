package com.bjtg.shortly.service;

import com.bjtg.shortly.dto.url.UrlRequest;
import com.bjtg.shortly.dto.url.UrlResponse;

public interface UrlService {
    UrlResponse shorUrl(UrlRequest urlRequest);
}
