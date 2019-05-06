package com.yx.gateway.feign;

import com.yx.gateway.feign.hystrix.Oauth2ClientHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Author: JST
 * @Date: 2019/4/27 14:34
 */
@FeignClient(value = "j-auth",fallback = Oauth2ClientHystrix.class)
public interface Oauth2Client {

    /**
     * 获取access_token
     * 这是spring-security-oauth2底层的接口，类TokenEndpoint
     * @param params
     * @return
     *
     * @see org.springframework.security.oauth2.provider.endpoint.TokenEndpoint
     */
    @PostMapping("/oauth/token")
    Map<String,Object> getAccessToken(@RequestParam Map<String,Object> params);

    @DeleteMapping("remove_token")
    void removeToken(@RequestParam("access_token") String access_token);


}
