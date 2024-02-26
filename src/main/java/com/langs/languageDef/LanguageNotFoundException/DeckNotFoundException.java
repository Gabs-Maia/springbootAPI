package com.langs.languageDef.LanguageNotFoundException;

public class DeckNotFoundException extends RuntimeException{
    public DeckNotFoundException(Long id){
        super("Could not find order" + id);
    }
}
