package com.bjtg.shortly.service;

import com.bjtg.shortly.model.Url;

import java.util.List;

public interface UrlManagerService {
    void saveUrl(String originalUrl);

    List<Url> getUrl(String urlOriginal); // cambiar model

}
