package com.example.story_service.controller;


import com.example.base_service.controller.BaseController;
import com.example.story_service.entity.Story;
import com.example.story_service.feign.SubscriptionFeignApi;
import com.example.story_service.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping()
public class StoryController extends BaseController<Story> {

    @Autowired
    private StoryService storyService;

    @Autowired
    private SubscriptionFeignApi subscriptionFeignApi;


    @GetMapping("/story_list_by_author_id/{author_id}")
    public List<Story> getStoryListByAuthorId(@PathVariable String author_id){
        return storyService.getStoryListByAuthorId(author_id);
    }

    @GetMapping("/story_list_by_subscriber_id/{subscriber_id}")
    public List<Story> getStoryListBySubscriberId(@PathVariable String subscriber_id){
        List<String> writerIdList = subscriptionFeignApi.getWriterIdListBySubscriberId(subscriber_id);

        return storyService.getStoryListByAuthorIdList(writerIdList);
    }

}
