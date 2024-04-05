package com.example.subscription_service.controller;


import com.example.base_service.controller.BaseController;
import com.example.subscription_service.entity.Subscription;
import com.example.subscription_service.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping()
public class SubscriptionController extends BaseController<Subscription> {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/subscriber_id_list_by_writer_id/{writer_id}")
    public List<String> getSubscriberIdListByWriterId(@PathVariable String writer_id){
        return subscriptionService.getSubscriberIdListByWriterId(writer_id);
    }

    @GetMapping("/writer_id_list_by_subscriber_id/{subscriber_id}")
    public List<String> getWriterIdListBySubscriberId(@PathVariable String subscriber_id){
        return subscriptionService.getWriterIdListBySubscriberId(subscriber_id);
    }
}
