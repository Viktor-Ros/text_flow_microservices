package com.example.text_flow_service.tests.story;

import com.example.text_flow_service.utils.entity.Story;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.*;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static com.example.text_flow_service.utils.TestData.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StoryTests {

    @Autowired
    private WebTestClient client;


    @Test
    @Order(1)
    public void saveStory() {
        EntityExchangeResult<Story> result =  client.post().uri("/story").body(Mono.just(new Story(ST_VALUE_NEW, EMP_1_ID)), Story.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(Story.class)
                .returnResult();

        ST_ID_NEW = Objects.requireNonNull(result.getResponseBody()).getId();
    }


    @Test
    @Order(2)
    public void updateStory() {
        client.put().uri("/story").body(Mono.just(new Story(ST_ID_NEW, ST_VALUE_UPDATE, EMP_1_ID)), Story.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("id").isEqualTo(ST_ID_NEW)
                .jsonPath("textValue").isEqualTo(ST_VALUE_UPDATE);
    }

    @Test
    @Order(3)
    public void deleteStoryById() {
        client.delete().uri("/story/" + ST_ID_NEW)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(String.class).isEqualTo("com.example.story_service.entity.Story with id = " + ST_ID_NEW + " successfully DELETED");
    }

    @Test
    @Order(4)
    public void getStoryById() {
        client.get().uri("/story/" + ST_1_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("id").isEqualTo(ST_1_ID)
                .jsonPath("textValue").isEqualTo(ST_1_VALUE);
    }

    @Test
    @Order(5)
    public void getAllStory() {
        client.get().uri("/story")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Story.class).hasSize(ST_ALL_LIST_SIZE);
    }

    @Test
    @Order(6)
    public void getStoryListByAuthorId() {
        client.get().uri("/story/story_list_by_author_id/" + EMP_1_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Story.class).hasSize(ST_LIST_SIZE_BY_AUTHOR_ID);
    }

    @Test
    @Order(7)
    public void getStoryListBySubscriberId() {
        client.get().uri("/story/story_list_by_subscriber_id/" + EMP_2_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Story.class).hasSize(ST_LIST_SIZE_BY_SUBSCRIBER_ID);
    }

}
