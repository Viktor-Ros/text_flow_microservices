package com.example.story_service.service;

import com.example.base_service.service.BaseService;
import com.example.story_service.dao.StoryDao;
import com.example.story_service.entity.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class StoryService extends BaseService<Story> {

    @Autowired
    private StoryDao storyDao;

    @Transactional
    public List<Story> getStoryListByAuthorId(String authorId){
        return storyDao.getStoryListByAuthorId(authorId);
    }

    @Transactional
    public List<Story> getStoryListByAuthorIdList(List<String> authorIdList){
        return storyDao.getStoryListByAuthorIdList(authorIdList);
    }

}
