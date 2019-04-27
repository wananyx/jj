package com.yx.user.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: JST
 * @Date: 2019/4/9 15:56
 * 角色与菜单关系表
 */
@Data
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sys_role_menu")
public class RoleMenuDO {

  @Id
  @ApiModelProperty(value = "角色id", dataType = "Long", name = "roleId", example = "12")
  private Long roleId;

  @ApiModelProperty(value = "菜单id", dataType = "Long", name = "menuId", example = "12")
  private Long menuId;

}
