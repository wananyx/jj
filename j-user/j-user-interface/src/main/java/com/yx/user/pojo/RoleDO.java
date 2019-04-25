package com.yx.user.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author: JST
 * @Date: 2019/4/9 15:56
 * 角色与菜单为一对多关系
 */
@Data
@ApiModel
@Table(name = "sys_role")
public class RoleDO {
    @Id
    @KeySql(useGeneratedKeys = true)
    @ApiModelProperty(value = "角色id",dataType = "Long", name = "id", example = "12")
    private Long id;

    @Length(max = 30, min = 2, message = "角色名长度只能在2-30之间")
    @ApiModelProperty(value = "角色名",dataType = "String", name = "roleName", example = "管理员")
    private String roleName;

    @ApiModelProperty(value = "创建时间",dataType = "Date", name = "created", example = "2019-04-09 14:29:30")
    private Date created;

    @ApiModelProperty(value = "更新时间",dataType = "Date", name = "updated", example = "2019-04-09 14:29:30")
    private Date updated;


}
