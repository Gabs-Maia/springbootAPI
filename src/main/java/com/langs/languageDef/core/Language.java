package com.langs.languageDef.core;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Arrays;
import java.util.Objects;

@Entity
public class Language{
    
    private @Id @GeneratedValue Long id;
    private String internationalName;
    private String originalName;
    private String[] spokenByRegions;
    private String langFamily;
    private Integer numSpeakers;
    private String sentenceOrder;
    private String alphabetType;
    private boolean hasCases;
    
     public Language(Long id, String internationalName, String originalName, String[] spokenByRegions,
                     
                     String langFamily, Integer numSpeakers, String sentenceOrder,
                     
                     String alphabetType, boolean hasCases) {
        
        this.id = id;
        this.internationalName = internationalName;
        this.originalName = originalName;
        this.spokenByRegions = spokenByRegions;
        this.langFamily = langFamily;
        this.numSpeakers = numSpeakers;
        this.sentenceOrder = sentenceOrder;
        this.alphabetType = alphabetType;
        this.hasCases = hasCases;
        
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getInternationalName() {
        return internationalName;
    }
    
    public void setInternationalName(String internationalName) {
        this.internationalName = internationalName;
    }
    
    public String getOriginalName() {
        return originalName;
    }
    
    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }
    
    public String[] getSpokenByRegions() {
        return spokenByRegions;
    }
    
    public void setSpokenByRegions(String[] spokenByRegions) {
        this.spokenByRegions = spokenByRegions;
    }
    
    public String getLangFamily() {
        return langFamily;
    }
    
    public void setLangFamily(String langFamily) {
        this.langFamily = langFamily;
    }
    
    public Integer getNumSpeakers() {
        return numSpeakers;
    }
    
    public void setNumSpeakers(Integer numSpeakers) {
        this.numSpeakers = numSpeakers;
    }
    
    public String getSentenceOrder() {
        return sentenceOrder;
    }
    
    public void setSentenceOrder(String sentenceOrder) {
        this.sentenceOrder = sentenceOrder;
    }
    
    public String getAlphabetType() {
        return alphabetType;
    }
    
    public void setAlphabetType(String alphabetType) {
        this.alphabetType = alphabetType;
    }
    
    public boolean isHasCases() {
        return hasCases;
    }
    
    public void setHasCases(boolean hasCases) {
        this.hasCases = hasCases;
    }
    
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Language language)) return false;
        
        return hasCases == language.hasCases && Objects.equals(id, language.id) && Objects.equals(internationalName, language.internationalName)
                
                && Objects.equals(originalName, language.originalName) && Arrays.equals(spokenByRegions, language.spokenByRegions)
                
                && Objects.equals(langFamily, language.langFamily) && Objects.equals(numSpeakers, language.numSpeakers)
                
                && Objects.equals(sentenceOrder, language.sentenceOrder) && Objects.equals(alphabetType, language.alphabetType);
    }
    
    @Override
    public int hashCode() {
        int result = Objects.hash(id, internationalName, originalName, langFamily, numSpeakers, sentenceOrder, alphabetType, hasCases);
        result = 31 * result + Arrays.hashCode(spokenByRegions);
        return result;
    }
    
    @Override
    public String toString() {
        return "Language{" +
                "id=" + id +
                ", internationalName='" + internationalName + '\'' +
                ", originalName='" + originalName + '\'' +
                ", spokenByRegions=" + Arrays.toString(spokenByRegions) +
                ", langFamily='" + langFamily + '\'' +
                ", numSpeakers=" + numSpeakers +
                ", sentenceOrder='" + sentenceOrder + '\'' +
                ", alphabetType='" + alphabetType + '\'' +
                ", hasCases=" + hasCases +
                '}';
    }
}