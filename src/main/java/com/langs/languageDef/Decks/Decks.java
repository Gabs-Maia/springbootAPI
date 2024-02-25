package com.langs.languageDef.Decks;

import com.langs.languageDef.Status.Status;
import com.langs.languageDef.core.Language;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "COSTUMER_ORDER")
public class Decks {
    
    private @Id @GeneratedValue Long id;
    
    private String description;
    private Status status;
    private Sentences sentences;
    private Language language;
    
    public Decks(Long id, String description, Status status, Sentences sentences, Language language) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.sentences = sentences;
        this.language = language;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Status getStatus() {
        return status;
    }
    
    public void setStatus(Status status) {
        this.status = status;
    }
    
    public Sentences getSentences() {
        return sentences;
    }
    
    public void setSentences(Sentences sentences) {
        this.sentences = sentences;
    }
    
    public Language getLanguage() {
        return language;
    }
    
    public void setLanguage(Language language) {
        this.language = language;
    }
    
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Decks decks)) return false;
        return Objects.equals(id, decks.id) && Objects.equals(description, decks.description) && Objects.equals(status, decks.status) && Objects.equals(sentences, decks.sentences) && Objects.equals(language, decks.language);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, description, status, sentences, language);
    }
    
    @Override
    public String toString() {
        return "Decks{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", sentences=" + sentences +
                ", language=" + language +
                '}';
    }
   