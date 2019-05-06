package com.yx.auth.feign.hystrix;

import com.yx.auth.feign.UserClient;
import com.yx.user.entity.LoginUser;
import com.yx.user.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: JST
 * @Date: 2019/5/6 9:37
 */
@Slf4j
@Component
public class UserClientHystrix implements UserClient {
    @Override
    public void register(SysUser user) {
        log.error("用户：{} 注册失败",user.getUsername());
    }

    @Override
    public LoginUser findByUsername(String username) {
        log.error("查询用户：{} 失败",username);
        return null;
    }

    @Override
    public List<SysUser> listUser() {
        log.error("查询用户列表失败");
        return null;
    }
}
