package com.yx.user.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import tk.mybatis.mapper.annotation.KeySql;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @Author: JST
 * @Date: 2019/4/9 15:56
 * 用户与角色为一对一关系
 */
@Data //此注解可以为属性生成get set方法
@ApiModel //此注解表明这个类是swagger的参数类
@Table(name = "sys_user") //此注解做ORM映射用
public class UserDO {
    @Id
    @KeySql(useGeneratedKeys = true)
    @ApiModelProperty(value = "用户id",dataType = "Long", name = "id", example = "12")
    private Long id;

    //字符串验证，在接收参数得时候加@Valid注解来验证
    @Length(max = 30, min = 4, message = "用户名长度只能在4-30之间")
    @ApiModelProperty(value = "用户名",dataType = "String", name = "username", example = "12")
    private String username;

    @Length(max = 100, min = 4, message = "密码长度只能在4-30之间")
    @ApiModelProperty(value = "密码",dataType = "String", name = "password", example = "12")
    private String password;

    @Length(max = 30, min = 2, message = "用户名长度只能在2-30之间")
    @ApiModelProperty(value = "昵称",dataType = "String", name = "nickname", example = "12")
    private String nickname;

    @ApiModelProperty(value = "头像url",dataType = "String", name = "imgUrl", example = "192.168.12.12:8888/1.jpg")
    private String imgUrl;

    @Pattern(regexp = "^1[35678]\\d{9}$",message = "手机号格式不正确")
    @ApiModelProperty(value = "电话",dataType = "String", name = "phone", example = "13888888888")
    private String phone;

    @ApiModelProperty(value = "性别",dataType = "String", name = "sex", example = "1 男 2 女")
    private String sex;

    @ApiModelProperty(value = "状态（1有效,0无效）",dataType = "Boolean", name = "enabled", example = "1有效,0无效")
    private String enabled;

    @ApiModelProperty(value = "类型",dataType = "String", name = "type", example = "手机/微信/账号密码")
    private String type;

    @ApiModelProperty(value = "创建时间",dataType = "Date", name = "created", example = "12")
    private Date created;

    @ApiModelProperty(value = "更新时间",dataType = "Date", name = "updated", example = "12")
    private Date updated;



}