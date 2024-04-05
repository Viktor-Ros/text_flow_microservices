package com.example.base_service.dao;


import com.example.base_service.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public abstract class BaseDao<T extends BaseEntity> {

    @Autowired
    protected EntityManager entityManager;

    private Class<T> clazz;


    public BaseDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Transactional
    public List<T> getAllList() {
        CriteriaQuery<T> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(clazz);
        criteriaQuery.from(clazz);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Transactional
    public T getById(String id) {
        return entityManager.find(clazz, id);
    }

    @Transactional
    public T save(T t) {
        return entityManager.merge(t);
    }

    @Transactional
    public String deleteById(String id) {
        CriteriaDelete<T> criteriaDelete = entityManager.getCriteriaBuilder().createCriteriaDelete(clazz);
        criteriaDelete.where(criteriaDelete.from(clazz).get("id").in(id));
        int result =  entityManager.createQuery(criteriaDelete).executeUpdate();

        return result != 0 ? clazz.getName() + " with id = " + id + " successfully DELETED" : clazz.getName() + " with id = " + id + " NOT EXIST";
    }


}
