package com.bjtg.shortly.dto.url;

import jakarta.validation.constraints.NotBlank;

public class ShortUrlRequest {

    @NotBlank(message = "url requerido")
    private String url;

    public ShortUrlRequest() {
    }

    public ShortUrlRequest(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
