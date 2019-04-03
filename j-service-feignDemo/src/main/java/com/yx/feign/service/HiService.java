package com.yx.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-hi",fallback = HiServiceHystric.class)
public interface HiService {
    @GetMapping("/hi/{name}")
    String sayHi(@PathVariable("name") String name);
}
