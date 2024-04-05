package com.example.story_service.entity;


import com.example.base_service.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Story extends BaseEntity {

    @Transient
    public static final String entityCode = "str";


    @Column(nullable = false)
    private String textValue;

    @Column(updatable = false)
    private String authorId;

    public Story() {
        super(entityCode);
    }

    public Story(String textValue, String authorId) {
        super(entityCode);
        this.textValue = textValue;
        this.authorId = authorId;
    }


    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "Story{" +
                "id=" + getId() +
                ", creationDate=" + getCreationDate() +
                ", modifyDate=" + getModifyDate() +
                ", textValue='" + textValue +
                ", authorId=" + authorId +
                '}';
    }
}
