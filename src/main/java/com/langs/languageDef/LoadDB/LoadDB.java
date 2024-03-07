package com.langs.languageDef.LoadDB;

import com.langs.languageDef.Decks.Decks;
import com.langs.languageDef.JPA.DeckRepository;
import com.langs.languageDef.JPA.LanguageRepository;
import com.langs.languageDef.JPA.SentenceRepository;
import com.langs.languageDef.Status.Status;
import com.langs.languageDef.core.Language;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDB{
    
    private static final Logger log = LoggerFactory.getLogger(LoadDB.class);
    
    @Bean
    CommandLineRunner initDataBase(LanguageRepository languageRepository, DeckRepository deckRepository){
        
        return args -> {
            /*Add data trough selenium...*/
            languageRepository.save(new Language());
            languageRepository.save(new Language());
            
            languageRepository.findAll().forEach(language -> log.info("Preloaded " + language));
            
            deckRepository.save(new Decks("", Status.COMPLETED));
            deckRepository.save(new Decks("", Status.IN_PROGRESS));
            
            deckRepository.findAll().forEach(decks -> {
                log.info("Preloaded " + decks);
            });
            
            
        };
    }
}