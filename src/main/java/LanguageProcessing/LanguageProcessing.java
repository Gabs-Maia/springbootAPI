package LanguageProcessing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Meta-annotation => component scanning ^ autoconfig => property support also.
@SpringBootApplication
public class LanguageProcessing {
    public static  void main(String... args){
        SpringApplication.run(LanguageProcessing.class, args);
    }
}
