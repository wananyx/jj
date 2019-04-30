package com.yx.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yx.user.entity.SysRole;
import com.yx.user.entity.SysUser;

import java.util.Set;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author jst
 * @since 2019-04-30
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    Set<SysRole> findRolesByUserId(Long userId);
}
