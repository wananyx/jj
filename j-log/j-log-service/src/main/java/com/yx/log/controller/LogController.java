package com.yx.log.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yx.common.vo.Result;
import com.yx.log.entity.Log;
import com.yx.log.service.ILogService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 日志管理
 * @author jst
 * @since 2019-05-09
 */
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private ILogService logService;

    /**
     * 保存日志
     * @param log
     */
    @ApiOperation(value = "保存日志",notes = "保存操作日志")
    @ApiImplicitParam(name = "日志对象",value = "log")
    @PostMapping("/anon/save")
    public void save(Log log){
        logService.save(log);
    }

    /**
     * 日志查询
     * @param params
     * @return
     */
    @GetMapping("/getList")
    public Result getList(Map<String,Object> params){
        IPage<Log> pageInfo = logService.selectPage(params);
        return Result.ok("page",pageInfo);
    }

}
