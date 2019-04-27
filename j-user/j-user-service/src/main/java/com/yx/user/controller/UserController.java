package com.yx.user.controller;

import com.yx.common.enums.ErrorEnums;
import com.yx.common.exception.JException;
import com.yx.common.utils.SecurityUtils;
import com.yx.common.vo.Result;
import com.yx.user.pojo.LoginUser;
import com.yx.user.pojo.UserDO;
import com.yx.user.service.UserServie;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Api(value = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServie userServie;

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
    public Result register(UserDO user){
        try {
            userServie.register(user);
            return Result.ok("注册成功");
        }catch (Exception e){
//            e.printStackTrace();
            throw new JException(ErrorEnums.INVALID_PARAM);
        }
//        return Result.error(400,"参数错误");
    }

    /**
     * 查询所有用户列表
     * @return
     */
    @ApiOperation(value = "查询所有用户列表",notes = "查询所有用户列表")
    @GetMapping("/listUser")
    public Result listUser(){
        try {
            List<UserDO> users = userServie.listUser();
            return Result.ok("listUser",users);
        }catch (Exception e){
            log.error("查询用户名单失败,{}",e.getMessage());
        }
        return Result.error(400,"查询失败");
    }

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @GetMapping("/findByUsername")
    public LoginUser findByUsername(String username){
        try {
            LoginUser loginUser = userServie.findByUsername(username);
            return loginUser;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
