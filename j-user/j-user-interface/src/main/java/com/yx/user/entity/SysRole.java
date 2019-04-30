package com.yx.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yx.common.vo.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 后台角色表
 * </p>
 *
 * @author jst
 * @since 2019-04-30
 */
@Data
@Accessors(chain = true)
@TableName(value = "sys_role")
public class SysRole extends BaseEntity {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 角色名英文
     */
    private String code;

    /**
     * 角色名
     */
    private String roleName;

    private Date created;

    private Date updated;


}
