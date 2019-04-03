package com.yx.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
@EnableTurbine
public class MonitorApplication {
    public static void main(String[] args) {
        SpringApplication.run(MonitorApplication.class,args);
    }
}
