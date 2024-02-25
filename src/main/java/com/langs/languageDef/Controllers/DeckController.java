package com.langs.languageDef.Controllers;

import com.langs.languageDef.JPA.DeckRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    
    private final DeckRepository deckRepository;
    private final DeckModelAssembler assembler;
    
    
}
