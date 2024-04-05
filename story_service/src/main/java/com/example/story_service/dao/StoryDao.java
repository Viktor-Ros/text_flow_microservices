package com.example.story_service.dao;

import com.example.base_service.dao.BaseDao;
import com.example.story_service.entity.Story;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;


@Repository
public class StoryDao extends BaseDao<Story> {
    public StoryDao() {
        super(Story.class);
    }

    @Transactional
    public List<Story> getStoryListByAuthorId(Object authorId){
        CriteriaQuery<Story> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Story.class);
        criteriaQuery.where(criteriaQuery.from(Story.class).get("authorId").in(authorId));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Transactional
    public List<Story> getStoryListByAuthorIdList(List<String> authorIdList){
        CriteriaQuery<Story> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Story.class);
        criteriaQuery.where(criteriaQuery.from(Story.class).get("authorId").in(authorIdList));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}
