package com.bjtg.shortly.error;

public class UrlNotFoundException extends RuntimeException {
    public UrlNotFoundException() {
        super("Not found");
    }
}
