package com.langs.languageDef.Assembler;

import com.langs.languageDef.Controllers.LanguageController;
import com.langs.languageDef.core.Language;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class LanguageModelAssembler implements RepresentationModelAssembler<Language, EntityModel<Language>>{

    @Override
    public EntityModel<Language> toModel(Language language){
        
        return EntityModel.of(language,
                linkTo(methodOn(LanguageController.class).one(language.getId())).withSelfRel(),
                linkTo(methodOn(LanguageController.class).all()).withRel("languages"));
    }
}
