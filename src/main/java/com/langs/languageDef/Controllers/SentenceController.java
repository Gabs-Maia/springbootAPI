package com.langs.languageDef.Controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.langs.languageDef.Assembler.LanguageModelAssembler;
import com.langs.languageDef.Assembler.SentenceModelAssembler;
import com.langs.languageDef.LanguageNotFoundException.SentenceNotFoundException;
import com.langs.languageDef.Status.Status;
import com.langs.languageDef.core.Sentences;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import com.langs.languageDef.JPA.SentenceRepository


@RestController
public class SentenceController {
    
    private final SentenceRepository sentenceRepository;
    private final SentenceModelAssembler assembler;
    
    SentenceController(SentenceRepository sentenceRepository, LanguageModelAssembler languageAssembler, SentenceModelAssembler assembler){
        
        this.sentenceRepository = sentenceRepository;
        this.assembler = assembler;
    }
    
    @GetMapping("/sentences")
    public CollectionModel<EntityModel<Sentences>> all() {
        
        List<EntityModel<Sentences>> sentences = sentenceRepository.findAll().stream()
                .map(assembler :: toModel)
                .collect(Collectors.toList());
        
        return CollectionModel.of(sentences,
                linkTo(methodOn(SentenceController.class).all()).withSelfRel());
    }
    
    @GetMapping("/sentences/{id}")
    public EntityModel<Sentences> one(@PathVariable Long id){llk
        
        Sentences sentences = sentenceRepository.findById(id)
                .orElseThrow(() -> new SentenceNotFoundException(id));
        
        return assembler.toModel(sentences);
    }
    
    @PostMapping("/order")
    ResponseEntity<EntityModel<Sentences>> newSentence(@RequestBody Sentences sentences){
        
        sentences.setStatus(Status.IN_PROGRESS);
        Sentences newSentence = sentenceRepository.save(sentences);
        
        return ResponseEntity.created(linkTo(methodOn(SentenceController.class).one(newSentence.getId())).toUri())
                .body(assembler.toModel(newSentence));
    }
    
    @DeleteMapping("/sentences/{id}/cancel")
    ResponseEntity<?> cancel(@PathVariable Long id){
        
        Sentences sentences = sentenceRepository.findById(id)
                .orElseThrow(() -> new SentenceNotFoundException(id));
        
        if(sentences.getStatus() == Status.IN_PROGRESS){
            
            sentences.setStatus(Status.CANCELLED);
            return ResponseEntity.ok(assembler.toModel(sentenceRepository.save(sentences)));
        }
        
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Method not allowed!")
                        .withDetail("You can't invoke a deck that is in the " + sentences.getStatus() + " status!"));
    }
    
    @PutMapping("/sentences/{id}/complete")
    ResponseEntity<?> complete(@PathVariable Long id){
        
        Sentences sentences = sentenceRepository.findById((id)
                .orElseThrow(() -> new SentenceNotFoundException(id));
        
        if (sentences.getStatus() == Status.IN_PROGRESS){
           
            sentences.getStatus(Status.COMPLETED);
            return ResponseEntity.ok(assembler.toModel(sentenceRepository.save(sentences)));
        }
        
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Method not Allowed!")
                        .withDetail("You can't complete an order that is in the" + sentences.getStatus() + " satus!"));
    }
}
