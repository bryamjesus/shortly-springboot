package com.bjtg.shortly.url.dto;

public class UrlResponse {
    private String originalUrl;
    private String shortCode;

    UrlResponse() {
    }

    UrlResponse(String shortCode, String originalUrl) {
        this.shortCode = shortCode;
        this.originalUrl = originalUrl;
    }

    public static UrlResponseBuilder builder() {
        return new UrlResponseBuilder();
    }

    public static class UrlResponseBuilder {
        private String originalUrl;
        private String shortCode;

        public UrlResponseBuilder originalUrl(String originalUrl) {
            this.originalUrl = originalUrl;
            return this;
        }

        public UrlResponseBuilder shortCode(String shortCode) {
            this.shortCode = shortCode;
            return this;
        }

        public UrlResponse build() {
            return new UrlResponse(this.shortCode, this.originalUrl);
        }
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortCode() {
        return shortCode;
    }
}
