package com.example.urlShortening.model;

import jakarta.persistence.*;

@Entity
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

    // Getters and setters
    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public Long getAccessCount() {
        return accessCount;
    }

    public void setAccessCount(Long accessCount) {
        this.accessCount = accessCount;
    }
    public void incrementAccessCount() {
        this.accessCount++;
    }


}
