package com.yx.common.vo;

import com.yx.common.enums.ErrorEnums;
import lombok.Data;

/**
 * 异常结果封装类
 */
@Data
public class ExceptionResult {

    private int code;
    private String msg;
    private Long timeStamp;

    public ExceptionResult(ErrorEnums e){
        this.code = e.code();
        this.msg = e.msg();
        this.timeStamp = System.currentTimeMillis();
    }



}
