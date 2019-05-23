package com.yx.log.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yx.log.entity.Log;

import java.util.Map;

/**
 * <p>
 * 日志表 服务类
 * </p>
 *
 * @author jst
 * @since 2019-05-09
 */
public interface ILogService extends IService<Log> {

    IPage<Log> selectPage(Map<String,Object> params);

}
