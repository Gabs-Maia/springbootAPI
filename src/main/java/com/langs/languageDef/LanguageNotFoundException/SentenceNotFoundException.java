package com.langs.languageDef.LanguageNotFoundException;

public class SentenceNotFoundException extends RuntimeException {
    
    public SentenceNotFoundException(Long id){
        
        super("Could not find sentence " + id);
    }
}
