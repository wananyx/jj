package com.yx.log.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yx.log.entity.Log;
import com.yx.log.mapper.LogMapper;
import com.yx.log.service.ILogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author jst
 * @since 2019-05-09
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

    @Override
    public boolean save(Log entity) {
        if(entity.getCreated()==null){
            entity.setCreated(new Date());
        }
        if(entity.getFlag()==null){
            entity.setFlag(Boolean.TRUE);
        }
        return super.save(entity);
    }


    @Override
    public IPage<Log> selectPage(Map<String, Object> params) {
        //当前页
        Long current = (Long) params.get("current");
        //每页条数
        Long size = (Long) params.get("size");
        //日志模块（条件查询）
        String module = String.valueOf(params.get("module"));
        //用户名 （条件查询）
        String username = String.valueOf(params.get("username"));

        //构建分页对象
        if(current ==null || size == null){
            current = 1L;
            size = 2L;
        }
        IPage<Log> logPage = new Page<>(current, size);
        //构建查询条件
        QueryWrapper<Log> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(!"null".equals(module),Log::getModule,module)//条件不为null则拼接，为null则不拼接
                .or()
                .eq(!"null".equals(username),Log::getUsername,username);
        //根据分页及查询条件进行查询
        IPage<Log> page = this.getBaseMapper().selectPage(logPage, wrapper);

        return page;
    }
}
