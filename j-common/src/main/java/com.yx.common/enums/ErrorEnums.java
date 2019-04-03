package com.yx.common.enums;

import lombok.Getter;
/**
 * 异常信息枚举
 */
@Getter
public enum ErrorEnums {

    ERROR(500,"服务器内部错误"),
    NOT_FOUND(404,"找不到资源啦"),

    ;


    private int code;
    private String message;

    ErrorEnums(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
