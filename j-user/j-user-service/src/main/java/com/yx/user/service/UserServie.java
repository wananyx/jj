package com.yx.user.service;

import com.yx.user.pojo.LoginUser;
import com.yx.user.pojo.UserDO;

import java.util.List;

public interface UserServie {

    void register(UserDO user);

    List<UserDO> listUser();

    LoginUser findByUsername(String username);
}
