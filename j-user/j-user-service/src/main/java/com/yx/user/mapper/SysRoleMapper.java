package com.yx.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yx.user.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * <p>
 * 后台角色表 Mapper 接口
 * </p>
 *
 * @author jst
 * @since 2019-04-30
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    Set<String> findPermsByRoleIds(@Param("roleIds") Set<Long> roleIds);

}
