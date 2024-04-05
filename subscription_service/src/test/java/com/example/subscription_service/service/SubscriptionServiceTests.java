package com.example.subscription_service.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.example.subscription_service.utils.TestData.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(properties = "eureka.client.enabled=false")
public class SubscriptionServiceTests {

    @Autowired
    SubscriptionService subscriptionService;


    @Test()
    @Order(1)
    public void getSubscriberIdListByWriterId(){
        List<String> subscriberIdList = subscriptionService.getSubscriberIdListByWriterId(EMP_1_ID);

        Assertions.assertNotNull(subscriberIdList, "Subscriber id list by writer id is NULL");
        Assertions.assertNotEquals(subscriberIdList.size(), 0, "Subscriber id list by writer id is EMPTY");
        Assertions.assertEquals(subscriberIdList.size(), SUB_LIST_BY_WRITER_SIZE, "Subscriber id list by writer id is WRONG size");
    }

    @Test()
    @Order(2)
    public void getWriterIdListBySubscriberId(){
        List<String> writerIdList = subscriptionService.getWriterIdListBySubscriberId(EMP_3_ID);

        Assertions.assertNotNull(writerIdList, "Writer id list by subscriber id is NULL");
        Assertions.assertNotEquals(writerIdList.size(), 0, "Writer id list by subscriber id is EMPTY");
        Assertions.assertEquals(writerIdList.size(), SUB_LIST_BY_SUBSCRIBER_SIZE, "Writer id list by subscriber id is WRONG size");
    }

}
