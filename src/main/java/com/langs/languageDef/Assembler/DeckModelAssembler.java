package com.langs.languageDef.Assembler;

import com.langs.languageDef.Decks.Decks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class DeckModelAssembler implements RepresentationModelAssembler<Decks, EntityModel<Decks>> {
    
    @Override
    public EntityModel<Decks> toModel(Decks decks) {
        
        EntityModel<Decks> deckModel = EntityModel.of(decks,
                linkTo(methodOn(D)));
    }
}
