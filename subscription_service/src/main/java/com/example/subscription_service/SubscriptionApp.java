package com.example.subscription_service;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableEurekaClient
@EnableJpaAuditing
@SpringBootApplication
@EnableFeignClients
public class SubscriptionApp {

    public static void main(String[] args) {
        SpringApplication.run(SubscriptionApp.class, args);
    }

}
