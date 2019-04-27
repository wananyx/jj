package com.yx.user.mapper;

import com.yx.common.mapper.BaseMapper;
import com.yx.user.pojo.RoleDO;
import com.yx.user.pojo.UserRoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * @Author: JST
 * @Date: 2019/4/24 10:24
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRoleDO,Long> {

    @Select(value = "select r.* from sys_role r inner join sys_user_role ur on r.id = ur.role_id where ur.user_id = #{userId}")
    Set<RoleDO> findRolesByUserId(@Param("userId") Long userId);

}
