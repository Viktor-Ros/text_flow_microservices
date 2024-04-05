package com.example.employee_service.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="subscription")
public interface SubscriptionFeignApi {

    @GetMapping("/subscriber_id_list_by_writer_id/{writer_id}")
    List<String> getSubscriberIdListByWriterId(@PathVariable String writer_id);

    @GetMapping("/writer_id_list_by_subscriber_id/{subscriber_id}")
    List<String> getWriterIdListBySubscriberId(@PathVariable String subscriber_id);

}
