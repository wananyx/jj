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
 * 用户与角色关系表
 */
@Data
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sys_user_role")
public class UserRoleDO {

  @Id
  @ApiModelProperty(value = "用户id", dataType = "Long", name = "userId", example = "12")
  private Long userId;
  @ApiModelProperty(value = "角色id", dataType = "Long", name = "roleId", example = "12")
  private Long roleId;


}
