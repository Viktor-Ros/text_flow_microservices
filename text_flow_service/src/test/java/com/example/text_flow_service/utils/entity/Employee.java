package com.example.text_flow_service.utils.entity;


import java.util.Date;

public class Employee {

    private String id;
    private Date creationDate;
    private Date modifyDate;
    private String name;


    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Employee(String name) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + getId() +
                ", creationDate=" + getCreationDate() +
                ", modifyDate=" + getModifyDate() +
                ", name='" + name +
                '}';
    }
}
