package com.langs.languageDef.LanguageNotFoundException;

public class LanguageNotFoundException extends RuntimeException {
    
    public LanguageNotFoundException(Long id){
        super("Could not find Language" + id);
    }
}
