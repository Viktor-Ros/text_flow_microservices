package com.example.text_flow_service.utils.entity;


import java.util.Date;


public class Subscription {

    private String id;
    private Date creationDate;
    private Date modifyDate;
    private String writerId;
    private String subscriberId;


    public Subscription() {
    }

    public Subscription(String id, String writerId, String subscriberId) {
        this.id = id;
        this.writerId = writerId;
        this.subscriberId = subscriberId;
    }

    public Subscription(String writerId, String subscriberId) {
        this.writerId = writerId;
        this.subscriberId = subscriberId;
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

    public String getWriterId() {
        return writerId;
    }

    public void setWriterId(String writerId) {
        this.writerId = writerId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + getId() +
                ", creationDate=" + getCreationDate() +
                ", modifyDate=" + getModifyDate() +
                ", writerId=" + writerId +
                ", subscriberId=" + subscriberId +
                '}';
    }
}


