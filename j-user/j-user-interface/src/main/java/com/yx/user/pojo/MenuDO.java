package com.yx.user.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: JST
 * @Date: 2019/4/9 15:56
 * 菜单权限类，与角色为多对一关系
 */
@Data
@ApiModel
@Table(name = "sys_menu")
public class MenuDO {

    @Id
    @KeySql(useGeneratedKeys = true)
    @ApiModelProperty(value = "菜单id",dataType = "Long", name = "menuId", example = "12")
    private Long menuId;

    @ApiModelProperty(value = "父级id",dataType = "Long", required = true, name = "parentId", example = "12")
    private Long parentId;

    @ApiModelProperty(value = "菜单名",dataType = "String", name = "name", example = "系统管理")
    private String name;

    @ApiModelProperty(value = "菜单url",dataType = "String", name = "url", example = "/sys")
    private String url;

    @ApiModelProperty(value = "菜单权限",dataType = "String", name = "perms",required = true, example = "sys:user")
    private String perms;

    @ApiModelProperty(value = "菜单类型 0:目录 1:菜单 2:按钮",dataType = "Integer",required = true, name = "type", example = "0")
    private Integer type;

    @ApiModelProperty(value = "菜单图标名",dataType = "String", name = "icon", example = "haha")
    private String icon;

    @ApiModelProperty(value = "排序",dataType = "Integer", name = "orderNum", example = "1")
    private Integer orderNum;

    @ApiModelProperty(value = "创建时间",dataType = "Date", name = "created", example = "2019-04-09 14:29:30")
    private Date created;

    @ApiModelProperty(value = "更新时间",dataType = "Date", name = "updated", example = "2019-04-09 14:29:30")
    private Date updated;


}
