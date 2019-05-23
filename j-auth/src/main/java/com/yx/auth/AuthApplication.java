package com.yx.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: JST
 * @Date: 2019/4/15 16:02
 */
@EnableCircuitBreaker//开启断路器功能
@EnableHystrix//启动熔断降级服务
@EnableHystrixDashboard//仪表盘功能
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
//@EnableApolloConfig
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class,args);
    }
}
