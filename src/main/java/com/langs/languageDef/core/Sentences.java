package com.langs.languageDef.core;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name= "LANG_SENTENCES")
public class Sentences {
    
    private @Id @GeneratedValue Long id;
    
    private String status;
    private String tag;
    private String language;
    private String sentence;
    private String sentenceTranslation;
    
    Sentences(){}
    
    
    public Sentences(Long id, String status, String tag,
                     String language, String sentence,
                     String sentenceTranslation) {
        
        this.id = id;
        this.status = status;
        this.tag = tag;
        this.language = language;
        this.sentence = sentence;
        this.sentenceTranslation = sentenceTranslation;
        
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getTag() {
        return this.tag;
    }
    
    public void setTag(String tag) {
        this.tag = tag;
    }
    
    public String getLanguage() {
        return this.language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }
    
    public String getSentence() {
        return sentence;
    }
    
    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
    
    public String getSentenceTranslation() {
        return this.sentenceTranslation;
    }
    
    public void setSentenceTranslation(String sentenceTranslation) {
        this.sentenceTranslation = sentenceTranslation;
    }
    
    @Override
    public boolean equals(Object object) {
        
        if (this == object) return true;
        if (!(object instanceof Sentences)) return false;
        
        Sentences sentences = (Sentences) object;
        
        return Objects.equals(this.id, sentences.id) && Objects.equals(this.status, sentences.status)
                && Objects.equals(this.tag, sentences.tag) && Objects.equals(this.language, sentences.language)
                && Objects.equals(this.sentence, sentences.sentence) && Objects.equals(this.sentenceTranslation, sentences.sentenceTranslation);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, status, tag, language, sentence, sentenceTranslation);
    }
    
    @Override
    public String toString() {
        return "Sentences{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", tag='" + tag + '\'' +
                ", language='" + language + '\'' +
                ", sentence='" + sentence + '\'' +
                ", sentenceTranslation='" + sentenceTranslation + '\'' +
                '}';
    }
}
