package com.bjtg.shortly.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bjtg.shortly.model.url.Url;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByShortCode(String codeUrl);

    Optional<Url> findByOriginalUrl(String codeUrl);
}
