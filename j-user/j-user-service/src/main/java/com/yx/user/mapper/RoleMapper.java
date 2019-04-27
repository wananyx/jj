package com.yx.user.mapper;

import com.yx.common.mapper.BaseMapper;
import com.yx.user.pojo.RoleDO;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @Author: JST
 * @Date: 2019/4/9 16:02
 */
public interface RoleMapper extends BaseMapper<RoleDO,Long> {

    Set<String> findPermsByRoleIds(@Param("roleIds") Set<Long> roleIds);
}
