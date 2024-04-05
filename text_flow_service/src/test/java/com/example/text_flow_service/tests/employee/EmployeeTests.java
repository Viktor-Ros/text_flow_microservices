package com.example.text_flow_service.tests.employee;

import com.example.text_flow_service.utils.entity.Employee;
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
public class EmployeeTests {

    @Autowired
    private WebTestClient client;


    @Test
    @Order(1)
    public void saveEmployee() {
        EntityExchangeResult<Employee> result =  client.post().uri("/employee").body(Mono.just(new Employee(EMP_NAME_NEW)), Employee.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(Employee.class)
                .returnResult();

        EMP_ID_NEW = Objects.requireNonNull(result.getResponseBody()).getId();
    }

    @Test
    @Order(2)
    public void updateEmployee() {
        client.put().uri("/employee").body(Mono.just(new Employee(EMP_ID_NEW, EMP_NAME_UPDATE)), Employee.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("id").isEqualTo(EMP_ID_NEW)
                .jsonPath("name").isEqualTo(EMP_NAME_UPDATE);
    }

    @Test
    @Order(3)
    public void deleteEmployeeById() {
        client.delete().uri("/employee/" + EMP_ID_NEW)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(String.class).isEqualTo("com.example.employee_service.entity.Employee with id = " + EMP_ID_NEW + " successfully DELETED");
    }

    @Test
    @Order(4)
    public void getEmployeeById() {
        client.get().uri("/employee/" + EMP_1_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("id").isEqualTo(EMP_1_ID)
                .jsonPath("name").isEqualTo(EMP_1_NAME);
    }

    @Test
    @Order(5)
    public void getAllEmployee() {
        client.get().uri("/employee")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Employee.class).hasSize(EMP_ALL_LIST_SIZE);
    }

    @Test
    @Order(6)
    public void getSubscriberListByWriterId() {
        client.get().uri("/employee/subscriber_list_by_writer_id/" + EMP_1_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Employee.class).hasSize(EMP_LIST_BY_WRITER_SIZE);
    }

    @Test
    @Order(7)
    public void getWriterListBySubscriberId() {
        client.get().uri("/employee/writer_list_by_subscriber_id/" + EMP_3_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Employee.class).hasSize(EMP_LIST_BY_SUBSCRIBER_SIZE);
    }

}
