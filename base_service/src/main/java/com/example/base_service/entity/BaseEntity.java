package com.example.base_service.entity;


import com.example.base_service.utils.IdGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = IdGenerator.NAME)
    @GenericGenerator(name = IdGenerator.NAME, strategy = IdGenerator.PATH)
    @Column(nullable = false)
    private String id;

    @CreatedDate
    @Column(updatable = false)
    private Date creationDate;

    @LastModifiedDate
    private Date modifyDate;

    public BaseEntity(String entityCode) {
        IdGenerator.setEntityCode(entityCode);
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

}
