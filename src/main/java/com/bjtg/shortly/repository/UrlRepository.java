package com.bjtg.shortly.repository;

import com.bjtg.shortly.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UrlRepository extends JpaRepository<Url, Long> {

    List<Url> findByShortCode(String codeUrl);

    //void saveUrl(Url url);

}
