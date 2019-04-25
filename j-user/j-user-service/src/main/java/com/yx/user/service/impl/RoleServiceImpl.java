package com.yx.user.service.impl;

import com.yx.common.enums.ErrorEnums;
import com.yx.common.exception.JException;
import com.yx.user.mapper.RoleMapper;
import com.yx.user.mapper.RoleMenuMapper;
import com.yx.user.pojo.RoleDO;
import com.yx.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Set;

/**
 * @Author: JST
 * @Date: 2019/4/9 16:06
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(RoleDO role) {
        role.setId(null);
        role.setCreated(new Date());
        int insert = roleMapper.insert(role);
        if(insert!=1){
            throw new JException(ErrorEnums.INVALID_PARAM);
        }
        //角色绑定权限
//        Set<Menu> menus = role.getMenus();
//        for (Menu menu: menus) {
//            RoleMenu roleMenu = new RoleMenu(role.getId(), menu.getMenuId());
//            roleMenuMapper.insert(roleMenu);
//        }

    }
}
