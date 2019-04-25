package com.yx.common.exception;

import com.yx.common.enums.ErrorEnums;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义异常类
 */
@Getter
public class JException extends RuntimeException {

    @Autowired
    private ErrorEnums errorEnums;

    public JException(ErrorEnums errorEnums){
        this.errorEnums = errorEnums;
    }
}
