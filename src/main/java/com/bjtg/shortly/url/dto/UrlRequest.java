package com.bjtg.shortly.url.dto;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;

public class UrlRequest {
    @NotBlank(message = "La URL es requerida")
    @URL(message = "Debe ser una URL válida")
    private String url;

    public UrlRequest() {
    }

    public UrlRequest(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
