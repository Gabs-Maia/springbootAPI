package com.langs.languageDef.JPA;

import com.langs.languageDef.core.Sentences;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SentenceRepository extends JpaRepository<Sentences, Long> {
}
