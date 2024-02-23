package com.langs.languageDef;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Language{
    
    private @Id @GeneratedValue Long id;
    private String name;
    private String langFamily;
    private Integer numberOfSpeakers;
    private String sentenceOrder;
    
    Language(){}
    
    public Language(String name, String langFamily, Integer numberOfSpeakers, String sentenceOrder){
        
        this.name = name;
        this.langFamily = langFamily;
        this.numberOfSpeakers = numberOfSpeakers;
        this.sentenceOrder = sentenceOrder;
    }
    
    public Long getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getFamily(){
        return this.langFamily;
    }
    public Integer getNumberOfSpeaker(){
        return this.numberOfSpeakers;
    }
    
    public String getSentenceOrder(){
        return this.sentenceOrder;
    }
    public void setId(Long id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setLangFamily(String langFamily){
        this.langFamily = langFamily;
    }
    public void setNumberOfSpeakers(Integer numberOfSpeakers){
        this.numberOfSpeakers = numberOfSpeakers;
    }
    public void setSentenceOrder( String sentenceOrder){
        this.sentenceOrder =sentenceOrder;
    }
    
    @Override
    public boolean equals(Object object){
        
        if(this == object)
            return true;
        if(!(object instanceof Language))
            return false;
        
        Language language = (Language) object;
        return Objects.equals(this.id, language.id) && Objects.equals(this.name, language.name)
        && Objects.equals(this.langFamily, language.langFamily) && Objects.equals(this.numberOfSpeakers, language.numberOfSpeakers)
        && Objects.equals(this.sentenceOrder, language.sentenceOrder);
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.name, this.langFamily, this.numberOfSpeakers, this.sentenceOrder);
    }
    
    @Override
    public String toString(){
        return "Language{" + "id = " + this.id + ", name = '" + this.name + '/'
                + ", Language Family = '" + this.langFamily + '\''+ "Num of Speakers = '" + this.numberOfSpeakers +
                '/' + "Sentence Order = " + this.sentenceOrder + " '}";
    }
}