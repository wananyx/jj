package com.yx.common.mapper;

import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * 通用Mapper和批量新增不是一个接口，在这统一继承一下
 * @param <T> 实体类的类型
 * @param <PK> 主键类型
 */
@RegisterMapper
public interface BaseMapper<T,PK> extends Mapper<T>,InsertListMapper<T>, IdListMapper<T,PK> {
}
