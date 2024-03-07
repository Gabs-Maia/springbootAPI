package com.langs.languageDef.Controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import com.langs.languageDef.JPA.DeckRepository;
import com.langs.languageDef.Decks.Decks;
import com.langs.languageDef.Status.Status;
import com.langs.languageDef.Assembler.DeckModelAssembler;
import com.langs.languageDef.LanguageNotFoundException.DeckNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeckController {
    
    private final DeckRepository deckRepository;
    private final DeckModelAssembler assembler;
    
    DeckController(DeckRepository deckRepository, DeckModelAssembler assembler){
    
        this.deckRepository = deckRepository;
        this.assembler = assembler;
    }
    
    @GetMapping("/decks")
    public CollectionModel<EntityModel<Decks>> all(){
        
        List<EntityModel<Decks>> decks = deckRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        
        return CollectionModel.of(decks,
                linkTo(methodOn(DeckController.class).all()).withSelfRel());
    }
    
    @GetMapping("/decks/{id}")
    public EntityModel<Decks> one(@PathVariable Long id) {
        
        Decks decks = deckRepository.findById(id).orElseThrow(() ->
                new DeckNotFoundException(id));
        
        return assembler.toModel(decks);
    }
    
    @PostMapping("/decks")
    ResponseEntity<EntityModel<Decks>> newDeck(@RequestBody Decks decks){
        
        decks.setStatus(Status.IN_PROGRESS);
        Decks newDeck = deckRepository.save(decks);
        
        return ResponseEntity
                .created(linkTo(methodOn(DeckController.class).one(newDeck.getId())).toUri())
                .body(assembler.toModel(newDeck));
    }
    
    @DeleteMapping("/decks/{id}/cancel")
    public ResponseEntity<?> cancel(@PathVariable Long id){
        
        Decks decks = deckRepository.findById(id)
                .orElseThrow(() -> new DeckNotFoundException(id));
        
        if(decks.getStatus() == Status.IN_PROGRESS){
            
            decks.setStatus(Status.CANCELLED);
            return ResponseEntity.ok(assembler.toModel(deckRepository.save(decks)));
        }
        
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Method not allowed")
                        .withDetail("You can't cancel an order that is in the" + decks.getStatus() + " status"));
    }
    
    @PutMapping("/decks/{id}/complete")
    public ResponseEntity<?> complete(@PathVariable Long id){
        
        Decks decks = deckRepository.findById(id)
                .orElseThrow(() -> new DeckNotFoundException(id));
        
        if(decks.getStatus() == Status.IN_PROGRESS){
            
            decks.setStatus(Status.COMPLETED);
            return ResponseEntity.ok(assembler.toModel(deckRepository.save(decks)));
        }
        
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Method not allowed")
                        .withDetail("You can't complete an order that is in the " + decks.getStatus() + " status"));
    }
}
