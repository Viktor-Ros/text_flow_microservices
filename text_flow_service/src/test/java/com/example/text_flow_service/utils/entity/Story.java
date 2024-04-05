package com.example.text_flow_service.utils.entity;


import java.util.Date;


public class Story {

    private String id;
    private Date creationDate;
    private Date modifyDate;
    private String textValue;
    private String authorId;


    public Story() {
    }

    public Story(String id, String textValue, String authorId) {
        this.id = id;
        this.textValue = textValue;
        this.authorId = authorId;
    }

    public Story(String textValue, String authorId) {
        this.textValue = textValue;
        this.authorId = authorId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
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
