package com.example.urlShortening.service;

import com.example.urlShortening.model.ShortUrl;
import com.example.urlShortening.repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class ShortUrlService {
    @Autowired
    private ShortUrlRepository shortUrlRepository;

    // Simple method to generate a random alphanumeric code
    public String generateCode(){
        int length = 6;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < length; i++) {
            code.append(characters.charAt(random.nextInt(characters.length())));
        }
        return code.toString();
    }

    // Create a new Short Url
    public ShortUrl createShortUrl(String OrignalUrl){
        String code = generateCode();

        //Checking the code is present in database or not ensuring uniqueness
        while(shortUrlRepository.findByCode(code).isPresent()){
            code=generateCode();
        }
        ShortUrl shortUrl=new ShortUrl(code,OrignalUrl);
        return shortUrl;
    }

    // Retrieve a short URL entry by code
    public Optional<ShortUrl> getShortUrl(String code){
        return shortUrlRepository.findByCode(code);
    }

    // Update the original URL of an existing short URL entry
    public ShortUrl updateShortUrl(String code,String newUrl){
        Optional<ShortUrl> shortUrl= shortUrlRepository.findByCode(code);
        if(shortUrl.isPresent()){
            ShortUrl prevUrl = shortUrl.get();
            prevUrl.setOriginalUrl(newUrl);
            return shortUrlRepository.save(prevUrl);
        }
        return null;
    }


    // Delete a short URL entry by its code
    public void deleteShortUrl(String code){
        shortUrlRepository.deleteByCode(code);
    }

    // Increment the access count for a short URL entry
    public void incrementAccessCount(ShortUrl shortUrl) {
        shortUrl.incrementAccessCount();
        shortUrlRepository.save(shortUrl);
    }
}
