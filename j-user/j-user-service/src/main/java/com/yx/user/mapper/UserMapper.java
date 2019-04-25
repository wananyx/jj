package com.yx.user.mapper;

import com.yx.user.pojo.RoleDO;
import com.yx.user.pojo.UserDO;
import tk.mybatis.mapper.common.Mapper;

import java.util.Set;

public interface UserMapper extends Mapper<UserDO> {

    Set<RoleDO> findRolesByUserId(Long userId);
}
