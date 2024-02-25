package com.langs.languageDef.Controllers;

import com.langs.languageDef.JPA.LanguageRepository;
import com.langs.languageDef.LanguageNotFoundException.LanguageNotFoundException;
import com.langs.languageDef.core.Language;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class LanguageController{
    
    private final LanguageRepository repository;
    
    LanguageController(LanguageRepository repository){
        this.repository = repository;
    }
    
    @GetMapping("/languages")
    CollectionModel<EntityModel<Language>> all() {
        
        List<EntityModel<Language>> languages = repository.findAll().stream()
                .map(language -> EntityModel.of(language,
                        linkTo(methodOn(LanguageController.class).one(language.getId())).withSelfRel(),
                        linkTo(methodOn(LanguageController.class).all()).withRel("employees")))
                .collect(Collectors.toList());
        
        return CollectionModel.of(languages, linkTo(methodOn(LanguageController.class).all()).withSelfRel());
    }
    
    Language newLanguage(@RequestBody Language newLanguage){
        
        return repository.save(newLanguage);
    }
    
    @GetMapping("/languages/{id}")
    EntityModel<Language> one(@PathVariable Long id){
        
        Language language = repository.findAllById(id)
                .orElseThrow(() -> new LanguageNotFoundException(id));
        
        return EntityModel.of(language,
                                linkTo(methodOn(LanguageController.class).one(id)).withSelfRel(),
                                linkTo(methodOn(LanguageController.class).all()).withRel("language"));
    }
    
    @PutMapping("/languages/{id}")
    Language replaceLanguage(@RequestBody Language newLanguage, @PathVariable Long id){
        
        return repository.findById (id)
                .map(language -> {
                        
                        language.setInternationalName(newLanguage.getInternationalName());
                        language.setLangFamily(newLanguage.getLangFamily());
                        return repository.save(language);
                        })
                
                .orElseGet(() -> {
                    
                    newLanguage.setId(id);
                    return repository.save(newLanguage);
                });
    }
    
    @DeleteMapping("/languages/{id}")
    void deleteLanguage(@PathVariable Long id){
        
        repository.deleteById(id);
    }
}