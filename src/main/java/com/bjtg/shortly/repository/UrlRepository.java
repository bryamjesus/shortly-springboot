package com.bjtg.shortly.repository;

import com.bjtg.shortly.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    List<Url> findByUrl(String url);

    void addUrl(Url url);

}
