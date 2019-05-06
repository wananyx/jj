package com.yx.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yx.user.entity.LoginUser;
import com.yx.user.entity.SysRole;
import com.yx.user.entity.SysUser;
import com.yx.user.mapper.SysRoleMapper;
import com.yx.user.mapper.SysUserMapper;
import com.yx.user.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author jst
 * @since 2019-04-30
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public LoginUser findUsername(String username) {
        if(StringUtils.isBlank(username)){
            throw new IllegalArgumentException("参数不能为空");
        }
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysUser::getUsername, username);
        List<SysUser> list = list(wrapper);
        //username唯一，所以list只可能有一个元素
        if(list.size()!=1){
            throw new IllegalArgumentException("该账号存在异常,请检查用户名是否重复");
        }

        SysUser user = list.get(0);
        user.setSex("0".equals(user.getSex())?"男":"女" );
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(user, loginUser);
        //根据用户id查询角色信息
        Set<SysRole> roles = userMapper.findRolesByUserId(user.getId());
        //如果分配了角色，则根据角色id集合查询权限信息
        if (roles.size() > 0) {
            //获取角色id集合
            Set<Long> roleIds = roles.parallelStream().map(SysRole::getId).collect(Collectors.toSet());
            Set<String> perms = roleMapper.findPermsByRoleIds(roleIds);
            loginUser.setRoles(roles);
            loginUser.setAuthority(perms);
        }
        return loginUser;
    }



    @Override
    public void register(SysUser user) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysUser::getUsername,user.getUsername());
        List<SysUser> list = list(wrapper);
        if(list.size()!=0){
            throw new IllegalArgumentException("该用户名已存在");
        }
        if(StringUtils.isBlank(user.getPassword().trim())){
            throw new IllegalArgumentException("请按要求正确输入密码");
        }
        user.setCreated(new Date());
        //设置加密密码
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        int insert = userMapper.insert(user);
        //如果插入数量不为1则证明新增失败
        if (insert != 1) {
            throw new RuntimeException("新增失败");
        }
    }

}
//org.apache.ibatis.binding.BindingException: Invalid bound statement (not found): com.yx.user.mapper.SysUserMapper.selectList