package com.example.text_flow_service.tests.subscription;

import com.example.text_flow_service.utils.entity.Subscription;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.text_flow_service.utils.TestData.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SubscriptionTests {

    @Autowired
    private WebTestClient client;


    @Test
    @Order(1)
    public void saveSubscription() {
        EntityExchangeResult<Subscription> result =  client.post().uri("/subscription").body(Mono.just(new Subscription(EMP_1_ID, EMP_2_ID)), Subscription.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(Subscription.class)
                .returnResult();

        SUB_ID_NEW = Objects.requireNonNull(result.getResponseBody()).getId();
    }

    @Test
    @Order(2)
    public void updateSubscription() {
        client.put().uri("/subscription").body(Mono.just(new Subscription(SUB_ID_NEW, EMP_1_ID, EMP_3_ID)), Subscription.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("id").isEqualTo(SUB_ID_NEW)
                .jsonPath("writerId").isEqualTo(EMP_1_ID)
                .jsonPath("subscriberId").isEqualTo(EMP_3_ID);
    }

    @Test
    @Order(3)
    public void deleteSubscriptionById() {
        client.delete().uri("/subscription/" + SUB_ID_NEW)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(String.class).isEqualTo("com.example.subscription_service.entity.Subscription with id = " + SUB_ID_NEW + " successfully DELETED");
    }

    @Test
    @Order(4)
    public void getSubscriptionById() {
        client.get().uri("/subscription/" + SUB_1_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("id").isEqualTo(SUB_1_ID)
                .jsonPath("writerId").isEqualTo(EMP_1_ID)
                .jsonPath("subscriberId").isEqualTo(EMP_2_ID);
    }

    @Test
    @Order(5)
    public void getAllSubscription() {
        client.get().uri("/subscription")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Subscription.class).hasSize(SUB_ALL_LIST_SIZE);
    }

}
