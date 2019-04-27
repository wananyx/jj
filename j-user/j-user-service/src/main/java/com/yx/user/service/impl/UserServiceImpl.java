package com.yx.user.service.impl;

import com.yx.common.enums.ErrorEnums;
import com.yx.common.exception.JException;
import com.yx.user.mapper.RoleMapper;
import com.yx.user.mapper.UserMapper;
import com.yx.user.pojo.LoginUser;
import com.yx.user.pojo.RoleDO;
import com.yx.user.pojo.UserDO;
import com.yx.user.service.UserServie;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserServie {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void register(UserDO user) {
        user.setId(null);
        user.setCreated(new Date());
        if(user.getNickname()==null){
            throw new JException(ErrorEnums.INVALID_PARAM);
        }
        //设置加密密码
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        int insert = userMapper.insert(user);
        //如果插入数量不为1则证明新增失败
        if (insert != 1) {
            throw new JException(ErrorEnums.INVALID_PARAM);
        }
    }

    @Override
    public List<UserDO> listUser() {
        return userMapper.selectAll();
    }

    @Override
    public LoginUser findByUsername(String username) {
        UserDO userDO = new UserDO();
        userDO.setUsername(username);
        //根据用户名查询用户
        UserDO user = userMapper.selectOne(userDO);
        if (user != null) {
            //将用户属性赋值到loginUser中
            LoginUser loginUser = new LoginUser();
            BeanUtils.copyProperties(user, loginUser);
            //查询用户角色信息
            Set<RoleDO> roles = userMapper.findRolesByUserId(user.getId());
            //查询用户权限信息
            if (roles != null) {
                //获取角色id集合
                Set<Long> roleIds = roles.parallelStream().map(RoleDO::getId).collect(Collectors.toSet());
                if (roleIds.size() != 0) {
                    //查询权限信息
                    Set<String> perms = roleMapper.findPermsByRoleIds(roleIds);
                    //将角色、权限信息设置给登陆用户
                    loginUser.setRoles(roles);
                    loginUser.setAuthority(perms);
                }
            }
            return loginUser;
        }
        return null;
    }
}
