package com.yx.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableCircuitBreaker
@EnableHystrix
@EnableHystrixDashboard
public class HiApplication {
    public static void main(String[] args) {
        SpringApplication.run(HiApplication.class,args);
    }

    @Value("${server.port}")
    private String port;

    @GetMapping("/hi/{name}")
    public String sayHi(@PathVariable String name){
        return "这是来自端口为"+port+"的"+name;
    }
}
