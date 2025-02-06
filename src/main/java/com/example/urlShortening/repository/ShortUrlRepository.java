package com.example.urlShortening.repository;

import com.example.urlShortening.model.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShortUrlRepository extends JpaRepository<ShortUrl,Long> {
    Optional<ShortUrl> findByCode(String code);
    void deleteByCode(String code);
}
