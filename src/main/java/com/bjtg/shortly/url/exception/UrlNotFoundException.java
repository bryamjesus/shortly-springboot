package com.bjtg.shortly.url.exception;

public class UrlNotFoundException extends RuntimeException {
    public UrlNotFoundException() {
        super("Not found");
    }
}
