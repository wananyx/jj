package com.yx.feign.controller;

import com.yx.feign.service.HiService;
import com.yx.feign.service.UserClient;
import com.yx.user.pojo.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    @Autowired
    private HiService hiService;

    @Autowired
    private UserClient userClient;

    @GetMapping("/demo")
    public LoginUser hello(String username){
        LoginUser byUsername = userClient.findByUsername(username);
        return byUsername;

    }

    @GetMapping("/hi/{name}")
    public String sayHi(@PathVariable String name){
        return hiService.sayHi(name);
    }


}
