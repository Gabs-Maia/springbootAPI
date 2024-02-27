package com.langs.languageDef.Controllers;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class RootController {
    
    @GetMapping
    RepresentationModel<?> index() {
        
        RepresentationModel<?> rootModel = new RepresentationModel<>();
        rootModel.add(linkTo(methodOn(DeckController.class).all()).withRel("languages"));
        rootModel.add(linkTo(methodOn(DeckController.class).all()).withRel("decks"));
        
        return rootModel;
    }
}
