package LoadDB;

import com.langs.languageDef.JPA.LanguageRepository;
import com.langs.languageDef.Language;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

public class LoadDB {
    
    private static final Logger log = LoggerFactory.getLogger(LoadDB.class);
    
    @Bean
    CommandLineRunner initDatabase(LanguageRepository repository){
        
        return args -> {
            log.info("Preloading " + repository.save(new Language("German", "Germanic", 8656328, "SOV")));
            log.info("Preloading " + repository.save(new Language("French", "Romanic", 9853321, "SVO")));
        };
    }
}
