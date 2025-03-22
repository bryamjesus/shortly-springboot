package com.bjtg.shortly.repository;

import com.bjtg.shortly.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {

    Optional<Url> findByShortCode(String codeUrl);

    //void saveUrl(Url url);

}
