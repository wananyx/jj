package com.yx.gateway.feign.hystrix;

import com.yx.gateway.feign.Oauth2Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: JST
 * @Date: 2019/5/6 16:15
 */
@Slf4j
@Component
public class Oauth2ClientHystrix implements Oauth2Client {
    @Override
    public Map<String, Object> getAccessToken(Map<String, Object> params) {
        String username = (String)params.get("username");
        log.error("用户：{} 登陆失败",username);
        return null;
    }

    @Override
    public void removeToken(String access_token) {
        log.error("退出登陆失败");
    }
}
