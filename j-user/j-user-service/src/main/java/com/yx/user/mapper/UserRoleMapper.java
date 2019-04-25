package com.yx.user.mapper;

import com.yx.user.pojo.RoleDO;
import com.yx.user.pojo.UserRoleDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.Set;

/**
 * @Author: JST
 * @Date: 2019/4/24 10:24
 */
@org.apache.ibatis.annotations.Mapper
public interface UserRoleMapper extends Mapper<UserRoleDO> {

    @Select(value = "select r.* from sys_role r inner join sys_user_role ur on r.id = ur.role_id where ur.user_id = #{userId}")
    Set<RoleDO> findRolesByUserId(@Param("userId") Long userId);

}
