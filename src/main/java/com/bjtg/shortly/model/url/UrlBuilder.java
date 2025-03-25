package com.bjtg.shortly.model.url;

import jakarta.persistence.*;

import java.util.Date;

public class UrlBuilder {
    private Long id;
    private String originalUrl;
    private String shortCode;
    private Integer hitCount;
    private Date createdAt;
    private Date modifiedAt;

    public UrlBuilder(String originalUrl, String shortCode) {
        this.originalUrl = originalUrl;
        this.shortCode = shortCode;
    }

    public UrlBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public UrlBuilder hitCount(Integer hitCount) {
        this.hitCount = hitCount;
        return this;
    }

    public UrlBuilder createdAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public UrlBuilder modifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }

    public Url build() {
        return new Url(this);
    }

    public Long getId() {
        return id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortCode() {
        return shortCode;
    }

    public Integer getHitCount() {
        return hitCount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }
}
