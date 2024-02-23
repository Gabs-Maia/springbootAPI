package com.langs.languageDef.Controllers;

import com.langs.languageDef.JPA.LanguageRepository;
import com.langs.languageDef.Language;
import com.langs.languageDef.LanguageNotFoundException.LanguageNotFoundException;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class LanguageController {
    
    private final LanguageRepository repository;
    
    LanguageController(LanguageRepository repository){
        
        this.repository = repository;
    }
    
    @GetMapping("/languages")
    Language newLanguage(@RequestBody Language newLanguage){
        return repository.save(newLanguage);
    }
    
    @GetMapping("/languages/{id}")
    EntityModel<Language> one(@PathVariable Long id){
        
        Language language = repository.findById(id)
                .orElseThrow(() -> new LanguageNotFoundException(id));
        
        return EntityModel.of(language,
                linkTo(methodOn(LanguageController.class).one(id)).withSelfRel(),
                linkTo(methodOn(LanguageController.class).one(id)).withRel("languages"));
                
    }
        
        @PutMapping("/languages/{id}")
        Language replaceLanguage(@RequestBody Language newLanguage, @PathVariable Long id){
            
            return repository.findById(id).map(language -> {
                        language.setName(newLanguage.getName());
                        language.setLangFamily(newLanguage.getFamily());
                        language.setNumberOfSpeakers(newLanguage.getNumberOfSpeaker());
                        language.setSentenceOrder(newLanguage.getSentenceOrder());
                        return repository.save(language);
                    }).orElseGet(() -> {
                       newLanguage.setId(id);
                       return repository.save(newLanguage);
                    });
        
        }
        
        @DeleteMapping("/languages/{id}")
        void deleteLanguage(@PathVariable Long id){
            
            repository.deleteById(id);
    }
}
