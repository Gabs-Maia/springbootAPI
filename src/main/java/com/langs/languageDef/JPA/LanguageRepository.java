package com.langs.languageDef.JPA;

import com.langs.languageDef.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
}
