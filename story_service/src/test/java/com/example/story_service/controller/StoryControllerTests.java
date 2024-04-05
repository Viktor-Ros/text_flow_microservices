package com.example.story_service.controller;

import com.example.story_service.entity.Story;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.example.story_service.utils.TestData.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(properties = "eureka.client.enabled=false")
public class StoryControllerTests {

    @Autowired
    MockMvc mockMvc;

    private static String TW_ID_NEW;


    @Test
    @Order(1)
    public void saveStory() throws Exception {
        MvcResult result = mockMvc.perform(post("/").content(new ObjectMapper().writeValueAsString((new Story(ST_VALUE_NEW, EMP_1_ID))))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andReturn();

        TW_ID_NEW = JsonPath.parse(result.getResponse().getContentAsString()).read("$.id");
    }

    @Test
    @Order(2)
    public void updateStory() throws Exception {
        Story updatedSStory = new Story();
        updatedSStory.setId(TW_ID_NEW);
        updatedSStory.setTextValue(ST_VALUE_UPDATE);
        updatedSStory.setAuthorId(EMP_1_ID);


        mockMvc.perform(put("/").content(new ObjectMapper().writeValueAsString((updatedSStory)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(TW_ID_NEW)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.textValue", is(ST_VALUE_UPDATE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.authorId", is(EMP_1_ID)));
    }


    @Test
    @Order(3)
    public void deleteStoryById() throws Exception {
        mockMvc.perform(delete("/" + TW_ID_NEW)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(Story.class.getName() + " with id = " + TW_ID_NEW + " successfully DELETED"));
    }

    @Test
    @Order(4)
    public void getStoryById() throws Exception {
        mockMvc.perform(get("/" + ST_1_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ST_1_ID))
                .andExpect(jsonPath("$.textValue").value(ST_1_VALUE));
    }


    @Test
    @Order(5)
    public void getAllStory() throws Exception {
        mockMvc.perform(get("/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(ST_ALL_LIST_SIZE));
    }

    @Test
    @Order(6)
    public void getStoryListByAuthorId() throws Exception {
        mockMvc.perform(get("/story_list_by_author_id/" + EMP_1_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(ST_LIST_SIZE_BY_AUTHOR_ID));
    }

    @Test
    @Order(7)
    @Disabled
    public void getStoryListBySubscriberId() throws Exception {
        mockMvc.perform(get("/story_list_by_subscriber_id/" + EMP_2_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(ST_LIST_SIZE_BY_SUBSCRIBER_ID));
    }

}
