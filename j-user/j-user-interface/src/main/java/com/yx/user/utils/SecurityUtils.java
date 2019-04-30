package com.yx.user.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yx.user.entity.LoginUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Map;

/**
 * @Author: JST
 * @Date: 2019/4/26 15:06
 * 获取当前登陆用户信息
 */
public class SecurityUtils {

    public static LoginUser getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication instanceof OAuth2Authentication){
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
            Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();
            if(userAuthentication instanceof UsernamePasswordAuthenticationToken){
                UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) userAuthentication;
                Object principal = token.getPrincipal();
                if(principal instanceof LoginUser){
                    return (LoginUser)principal;
                }
                Map map = (Map) token.getDetails();
                map = (Map)map.get("principal");
                return JSONObject.parseObject(JSON.toJSONString(map),LoginUser.class);
            }
        }
        return null;
    }
}
