package com.example.employee_service.entity;


import com.example.base_service.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Employee extends BaseEntity {

    @Transient
    public static final String entityCode = "emp";

    private String name;

    public Employee() {
        super(entityCode);
    }

    public Employee(String name) {
        super(entityCode);
        this.name = name;
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
