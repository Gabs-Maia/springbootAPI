package com.langs.languageDef.Assembler;

import com.langs.languageDef.Controllers.SentenceController;
import com.langs.languageDef.core.Sentences;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class SentenceModelAssembler implements RepresentationModelAssembler<Sentences, EntityModel<Sentences>> {
    
    @Override
    public EntityModel<Sentences> toModel(Sentences sentences){
        
        return EntityModel.of(sentences,
                
                linkTo(methodOn(SentenceController.class).one(sentences.getId())).withSelfRel(),
                linkTo(methodOn(SentenceController.class).all()).withRel("sentences"));
    }
}
