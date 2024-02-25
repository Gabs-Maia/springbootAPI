package com.langs.languageDef.LoadDB;

import com.langs.languageDef.JPA.LanguageRepository;
import com.langs.languageDef.core.Language;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Entity
public class LoadDB {
    
    private static final Logger log = LoggerFactory.getLogger(LoadDB.class);
    
    @Bean
    CommandLineRunner initDatabase(LanguageRepository repository){
        
        return args -> {
            languageRrepository.save(new Language(
                    "", "German",
                    
                    "Deutsch", "Europe", "Germanic",
                    
                    984178799, "SVO", "Latin",false
            )));
            languageRepository.save(new Language("French", "French",
                    
                    "Français", "Europe", "Romance",
                    
                    984178799, "SVO", "Latin",false));
        
            languageRepository.findAll().forEach(language -> log.info("Preloaded" + language));
            
            
            
        };
    }
}
