package com.yx.user.service.impl;

import com.yx.common.enums.ErrorEnums;
import com.yx.common.exception.JException;
import com.yx.user.mapper.MenuMapper;
import com.yx.user.pojo.MenuDO;
import com.yx.user.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: JST
 * @Date: 2019/4/9 16:07
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public void save(MenuDO menu) {
        menu.setMenuId(null);
        menu.setCreated(new Date());
        int insert = menuMapper.insert(menu);
        if(insert!=1){
            throw new JException(ErrorEnums.INVALID_PARAM);
        }
    }
}
