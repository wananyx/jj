package com.yx.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色与菜单对应关系
 * </p>
 *
 * @author jst
 * @since 2019-04-30
 */
@Data
@Accessors(chain = true)
@TableName(value = "sys_role_menu")
public class SysRoleMenu extends BaseEntity {

    /**
     * 角色ID
     */
    @TableId(type = IdType.NONE)
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;


}
