package com.yx.common.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 异常信息枚举
 */
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorEnums {

    ERROR(500,"服务器内部错误"),
    NOT_FOUND(404,"找不到资源啦"),
    INVALID_PARAM(400, "参数错误"),
    ;


    private int code;
    private String message;

    public int code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }
}
