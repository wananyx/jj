package com.yx.common.exception;

import com.yx.common.vo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**
 * 全局异常处理类，自定义异常类JException，具体异常信息使用枚举方式展示ErrorEnums，异常结果使用ExceptionResult封装返回
 */
@ControllerAdvice
public class BasicExceptionHandler {

    @ExceptionHandler(JException.class)
    public ResponseEntity<ExceptionResult> exceptionHandler(JException e){
        return ResponseEntity.status(e.getErrorEnums().code()).body(new ExceptionResult(e.getErrorEnums()));
    }

}
