package com.langs.languageDef.Assembler;

import com.langs.languageDef.Controllers.DeckController;
import com.langs.languageDef.Decks.Decks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DeckModelAssembler implements RepresentationModelAssembler<Decks, EntityModel<Decks>> {
    
    @Override
    public EntityModel<Decks> toModel(Decks decks) {
        
        EntityModel<Decks> deckModel = EntityModel.of(decks,
                linkTo(methodOn(DeckController.class).cancel(decks.getId())).withSelfRel(),
                linkTo(methodOn(DeckController.class).all()).withRel("language"));
        
        if(decks.getStatus() == Status.IN_PROGRESS){
            
            deckModel.add(linkTo(methodOn(DeckController.class).one(decks.getId())).withRel("cancel"));
            deckModel.add(linkTo(methodOn(DeckController.class).complete(decks.getId())).withRel("complete"));
        }
        
        return deckModel;
    }
}
