package com.bjtg.shortly.dto.url;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.antlr.v4.runtime.misc.NotNull;

public class UrlRequest {

    @NotBlank(message = "url requerido")
    @Size()
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
