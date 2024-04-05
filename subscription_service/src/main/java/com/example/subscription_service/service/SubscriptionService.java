package com.example.subscription_service.service;

import com.example.base_service.service.BaseService;
import com.example.subscription_service.dao.SubscriptionDao;
import com.example.subscription_service.entity.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class SubscriptionService extends BaseService<Subscription> {

    @Autowired
    private SubscriptionDao subscriptionDao;

    public List<String> getSubscriberIdListByWriterId(String writer_id){
        return subscriptionDao.getSubscriptionListByWriterId(writer_id).stream().map(Subscription::getSubscriberId).collect(Collectors.toList());
    }

    public List<String> getWriterIdListBySubscriberId(String subscriber_id){
        return subscriptionDao.getSubscriptionListBySubscriberId(subscriber_id).stream().map(Subscription::getWriterId).collect(Collectors.toList());
    }
}
