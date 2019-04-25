package com.yx.feign.service;

import api.UserApi;
import com.yx.user.pojo.LoginUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: JST
 * @Date: 2019/4/23 17:49
 */
@FeignClient(value = "j-user-service")
public interface UserClient extends UserApi {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @GetMapping("/user/findByUsername")
    LoginUser findByUsername(@RequestParam("username") String username);
}
