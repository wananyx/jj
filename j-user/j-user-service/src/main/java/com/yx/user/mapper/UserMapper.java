package com.yx.user.mapper;

import com.yx.common.mapper.BaseMapper;
import com.yx.user.pojo.RoleDO;
import com.yx.user.pojo.UserDO;

import java.util.Set;

public interface UserMapper extends BaseMapper<UserDO,Long> {

    Set<RoleDO> findRolesByUserId(Long userId);
}
