package com.example.story_service.service;

import com.example.story_service.entity.Story;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static com.example.story_service.utils.TestData.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(properties = "eureka.client.enabled=false")
public class StoryServiceTests {

    @Autowired
    StoryService storyService;


    @Test()
    @Order(1)
    public void getStoryListByAuthorId(){
        List<Story> storyList = storyService.getStoryListByAuthorId(EMP_1_ID);

        Assertions.assertNotNull(storyList, "Story list by author id is NULL");
        Assertions.assertNotEquals(storyList.size(), 0, "Story list by author id is EMPTY");
        Assertions.assertEquals(storyList.size(), ST_LIST_SIZE_BY_AUTHOR_ID, "Story list by author id is WRONG size");
    }

    @Test()
    @Order(2)
    public void getStoryListBySubscriberId(){
        List<String> authorIdList = new ArrayList<>();
        authorIdList.add(EMP_1_ID);
        authorIdList.add(EMP_2_ID);

        List<Story> storyList = storyService.getStoryListByAuthorIdList(authorIdList);

        Assertions.assertNotNull(storyList, "Story list by author id is NULL");
        Assertions.assertNotEquals(storyList.size(), 0, "Story list by author id list is EMPTY");
        Assertions.assertEquals(storyList.size(), ST_LIST_SIZE_BY_AUTHOR_ID_LIST, "Story list by author id list is WRONG size");
    }

}
