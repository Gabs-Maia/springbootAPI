package com.langs.languageDef.LnaguageNotFoundAdvie;

import com.langs.languageDef.LanguageNotFoundException.LanguageNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class LanguageNotFoundAdvice {
    
    @ResponseBody
    @ExceptionHandler(LanguageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String languageNotFoundHandler(LanguageNotFoundException ex){
        return ex.getMessage();
    }

}
