package com.yx.user.controller;


import com.yx.common.vo.Result;
import com.yx.user.entity.LoginUser;
import com.yx.user.entity.SysUser;
import com.yx.user.service.ISysUserService;
import com.yx.user.utils.SecurityUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author jst
 * @since 2019-04-30
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private ISysUserService userService;

    /**
     * 获取当前登陆用户
     * @return
     */
    @ApiOperation(value = "获取当前登陆用户",notes = "获取当前登陆用户",httpMethod = "GET",response = LoginUser.class)
    @GetMapping("/current")
    public LoginUser getLoginUser(){
        return SecurityUtils.getLoginUser();
    }

    /**
     * 注册用户
     * @param user User实体类
     * @return
     */
    @ApiOperation(value = "注册新用户",notes = "注册新用户")
    @ApiImplicitParam(name = "user", value = "User实体类",required = true, dataType = "User")
    @PostMapping("/register")
    public Result register(SysUser user){
        userService.register(user);
        log.info("创建账号 {}",user.getUsername());
        return Result.ok();
    }

    /**
     * 查询所有用户列表
     * @return
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "查询所有用户列表",notes = "查询所有用户列表")
    @GetMapping("/listUser")
    public Result listUser(){
        List<SysUser> list = userService.list();
        return Result.ok("list",list);
    }


    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @GetMapping("/anon/findUsername")
    public LoginUser findUsername(String username){
        LoginUser user = userService.findUsername(username);
        return user;
    }


}
