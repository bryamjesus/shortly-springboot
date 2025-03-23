package com.bjtg.shortly.model.url;

import java.util.Date;

public class UrlBuilder {
    private final Url url;

    public UrlBuilder() {
        this.url = new Url();
    }

    public UrlBuilder id(Long id) {
        url.setId(id);
        return this;
    }

    public UrlBuilder originalUrl(String originalUrl) {
        url.setOriginalUrl(originalUrl);
        return this;
    }

    public UrlBuilder shortCode(String shortCode) {
        url.setShortCode(shortCode);
        return this;
    }

    public UrlBuilder hitCount(Integer hitCount) {
        url.setHitCount(hitCount);
        return this;
    }

    public UrlBuilder createdAt(Date createdAt) {
        url.setCreatedAt(createdAt);
        return this;
    }

    public UrlBuilder modifiedAt(Date modifiedAt) {
        url.setModifiedAt(modifiedAt);
        return this;
    }

    public Url build() {
        return url;
    }
}
