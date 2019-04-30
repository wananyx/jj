package com.yx.user.exception;

import com.yx.common.vo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: JST
 * @Date: 2019/4/28 17:25
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e){
        return new Result(500,"服务器崩溃");
    }

    @ResponseBody
    @ExceptionHandler(JException.class)
    public Result myExceptionHandler(JException e){
        return new Result(e.getStatusCode(),e.getMessage());
    }
}
