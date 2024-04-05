package com.example.subscription_service.dao;

import com.example.subscription_service.entity.Subscription;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.example.subscription_service.utils.TestData.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(properties = "eureka.client.enabled=false")
public class SubscriptionDaoTests {

    @Autowired
    SubscriptionDao subscriptionDao;

    private static String SUB_ID_NEW;


    @Test()
    @Order(1)
    public void save(){
        Subscription savedSubscription = subscriptionDao.save(new Subscription(EMP_1_ID, EMP_2_ID));

        Assertions.assertNotNull(savedSubscription, "Subscription is NOT saved");
        Assertions.assertNotEquals(savedSubscription.getId(), 0, "Subscription was created INCORRECT");

        SUB_ID_NEW = savedSubscription.getId();
    }

    @Test
    @Order(2)
    public void getById(){
        Subscription subscription = subscriptionDao.getById(SUB_1_ID);

        Assertions.assertNotNull(subscription, "Subscription is NOT get by id");
    }

    @Test
    @Order(3)
    public void delete(){
        String message = subscriptionDao.deleteById(SUB_ID_NEW);

        Assertions.assertEquals( Subscription.class.getName() +" with id = " + SUB_ID_NEW + " successfully DELETED", message,"Subscription is NOT deleted");
    }

    @Test
    @Order(4)
    public void getAll(){
        List<Subscription> subscriptionList = subscriptionDao.getAllList();

        Assertions.assertNotNull(subscriptionList, "All Subscription list is NULL");
        Assertions.assertNotEquals(subscriptionList.size(), 0, "All Subscription list is EMPTY");
        Assertions.assertEquals(subscriptionList.size(), SUB_ALL_LIST_SIZE, "All Subscription list is WRONG size");
    }

    @Test
    @Order(5)
    public void getSubscriptionListByWriterId(){
        List<Subscription> subscriptionList = subscriptionDao.getSubscriptionListByWriterId(EMP_1_ID);

        Assertions.assertNotNull(subscriptionList, "Subscription list by writer id is NULL");
        Assertions.assertNotEquals(subscriptionList.size(), 0, "Subscription list by writer id is EMPTY");
        Assertions.assertEquals(subscriptionList.size(), SUB_LIST_BY_WRITER_SIZE, "Subscription list by writer id is WRONG size");
    }

    @Test
    @Order(6)
    public void getSubscriptionListBySubscriberId(){
        List<Subscription> subscriptionList = subscriptionDao.getSubscriptionListBySubscriberId(EMP_3_ID);

        Assertions.assertNotNull(subscriptionList, "Subscription list  by subscriber id is NULL");
        Assertions.assertNotEquals(subscriptionList.size(), 0, "Subscription list  by subscriber id is EMPTY");
        Assertions.assertEquals(subscriptionList.size(), SUB_LIST_BY_SUBSCRIBER_SIZE, "Subscription list  by subscriber id is WRONG size");
    }

}
