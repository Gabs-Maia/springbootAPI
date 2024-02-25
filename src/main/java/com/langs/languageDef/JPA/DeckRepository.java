package com.langs.languageDef.JPA;

import com.langs.languageDef.Decks.Decks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeckRepository extends JpaRepository<Decks, Long> {

}
