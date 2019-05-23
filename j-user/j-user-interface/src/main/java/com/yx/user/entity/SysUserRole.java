package com.yx.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author jst
 * @since 2019-04-30
 */
@Data
@Accessors(chain = true)
@TableName(value = "sys_user_role")
public class SysUserRole extends BaseEntity {

    /**
     * 用户id
     */
    @TableId(type = IdType.NONE)
    private Integer userId;

    /**
     * 角色id
     */
    private Integer roleId;


}
