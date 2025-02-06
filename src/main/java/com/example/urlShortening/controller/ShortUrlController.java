package com.example.urlShortening.controller;

import com.example.urlShortening.dto.ShortUrlRequest;
import com.example.urlShortening.model.ShortUrl;
import com.example.urlShortening.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/urls")
public class ShortUrlController {
    @Autowired
    private ShortUrlService service;

    // Create a new short URL.
    // Expects a JSON string payload (for simplicity, the request body is just the original URL in quotes).
    @PostMapping
    public ResponseEntity<ShortUrl> createShortUrl(@RequestBody ShortUrlRequest request) {
        // Remove any unwanted quotes if necessary
        String originalUrl = request.getOriginalUrl();
        ShortUrl shortUrl = service.createShortUrl(originalUrl);
        return ResponseEntity.created(URI.create("/api/urls/" + shortUrl.getCode())).body(shortUrl);
    }

    // Retrieve details of a short URL by its code
    @GetMapping("/{code}")
    public ResponseEntity<ShortUrl> getShortUrl(@PathVariable String code){
        Optional<ShortUrl> shortUrl = service.getShortUrl(code);
        return shortUrl.map(ResponseEntity ::ok).orElse(ResponseEntity.notFound().build());
    }


    // Update the original URL for an existing short URL
    @PutMapping("/{code}")
    public ResponseEntity<ShortUrl> updateShortUrl(@PathVariable String code,@RequestBody String newUrl){
        ShortUrl updateShortUrl = service.updateShortUrl(code, newUrl);
        if (updateShortUrl == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updateShortUrl);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteShortUrl(@PathVariable String code){
        Optional<ShortUrl> shortUrl=service.getShortUrl(code);
        if(shortUrl.isPresent()){
            service.deleteShortUrl(code);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Redirect endpoint:
    // When a short URL is visited, increment its access count and redirect to the original URL.
    @GetMapping("/r/{code}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String code){
        Optional<ShortUrl> shortUrlOpt = service.getShortUrl(code);
        if (shortUrlOpt.isPresent()) {
            ShortUrl shortUrl = shortUrlOpt.get();
            service.incrementAccessCount(shortUrl);
            return ResponseEntity.status(302)
                    .location(URI.create(shortUrl.getOriginalUrl()))
                    .build();
        }
        return ResponseEntity.notFound().build();
    }

    // Get statistics for a short URL (currently returns the full ShortUrl object including access count)
    @GetMapping("/{code}/stats")
    public ResponseEntity<ShortUrl> getStatistics(@PathVariable String code) {
        Optional<ShortUrl> shortUrlOpt = service.getShortUrl(code);
        return shortUrlOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
