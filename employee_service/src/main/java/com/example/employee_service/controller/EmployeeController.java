package com.example.employee_service.controller;


import com.example.base_service.controller.BaseController;
import com.example.employee_service.feign.SubscriptionFeignApi;
import com.example.employee_service.entity.Employee;
import com.example.employee_service.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class EmployeeController extends BaseController<Employee> {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SubscriptionFeignApi subscriptionFeignApi;


    @GetMapping("/subscriber_list_by_writer_id/{writer_id}")
    public List<Employee> getSubscriberListByWriterId(@PathVariable String writer_id){
        List<String> subscriberIdList = subscriptionFeignApi.getSubscriberIdListByWriterId(writer_id);

        return employeeService.getEmployeeListByIdList(subscriberIdList);
    }

    @GetMapping("/writer_list_by_subscriber_id/{subscriber_id}")
    public List<Employee> getWriterListBySubscriberId(@PathVariable String subscriber_id){
        List<String> writerIdList = subscriptionFeignApi.getWriterIdListBySubscriberId(subscriber_id);

        return employeeService.getEmployeeListByIdList(writerIdList);
    }

}
