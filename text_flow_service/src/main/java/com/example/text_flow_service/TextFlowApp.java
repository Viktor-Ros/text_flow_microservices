package com.example.text_flow_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class TextFlowApp {

    public static void main(String[] args) {
        SpringApplication.run(TextFlowApp.class, args);
    }
}
