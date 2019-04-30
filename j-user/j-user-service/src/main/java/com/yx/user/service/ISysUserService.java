package com.yx.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yx.user.entity.LoginUser;
import com.yx.user.entity.SysUser;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author jst
 * @since 2019-04-30
 */
public interface ISysUserService extends IService<SysUser> {

    LoginUser findUsername(String username);

    void register(SysUser user);
}
