package com.yx.user.controller;

import com.yx.common.enums.ErrorEnums;
import com.yx.common.exception.JException;
import com.yx.common.vo.Result;
import com.yx.user.pojo.RoleDO;
import com.yx.user.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author: JST
 * @Date: 2019/4/9 17:04
 */
@Api(value = "角色管理")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "新建角色",notes = "新建角色")
    @ApiImplicitParam(name = "role", value = "Role实体类",required = true, dataType = "Role")
    @PostMapping("/save")
    public Result save(@Valid RoleDO role){
        try {
            roleService.save(role);
            return Result.ok("新增角色成功");
        }catch (Exception e){
            e.printStackTrace();
            throw new JException(ErrorEnums.INVALID_PARAM);
        }

    }
}
