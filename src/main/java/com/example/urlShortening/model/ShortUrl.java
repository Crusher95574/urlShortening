package com.example.urlShortening.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ShortUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    private String originalUrl;

    private Long accessCount=0L;

    // Constructors
    public ShortUrl() {}

    public ShortUrl(String code, String originalUrl) {
        this.code = code;
        this.originalUrl = originalUrl;
        this.accessCount = 0L;
    }

    public void incrementAccessCount() {
        this.accessCount++;
    }

}
