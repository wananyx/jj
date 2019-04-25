package com.yx.user.controller;

import com.yx.common.enums.ErrorEnums;
import com.yx.common.exception.JException;
import com.yx.common.vo.Result;
import com.yx.user.pojo.MenuDO;
import com.yx.user.service.MenuService;
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
 * @Date: 2019/4/9 17:26
 */
@Api(value = "菜单管理")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "新建菜单",notes = "新建菜单")
    @ApiImplicitParam(name = "menu", value = "Menu实体类",required = true, dataType = "Menu")
    @PostMapping("/save")
    public Result save(@Valid MenuDO menu){
        try {
            menuService.save(menu);
            return Result.ok("新增菜单成功");
        }catch (Exception e){
            e.printStackTrace();
            throw new JException(ErrorEnums.INVALID_PARAM);
        }
    }

}
