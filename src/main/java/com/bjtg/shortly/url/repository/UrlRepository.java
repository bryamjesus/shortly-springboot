package com.bjtg.shortly.url.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bjtg.shortly.url.model.Url;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByShortCode(String codeUrl);

    Optional<Url> findByOriginalUrl(String codeUrl);
}
