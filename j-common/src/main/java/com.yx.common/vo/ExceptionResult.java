package com.yx.common.vo;

import com.yx.common.enums.ErrorEnums;
import lombok.Data;

/**
 * 异常结果封装类
 */
@Data
public class ExceptionResult {

    private int errCode;
    private String errorMsg;
    private Long timeStamp;

    public ExceptionResult(ErrorEnums e){
        this.errCode = e.getCode();
        this.errorMsg = e.getMessage();
        this.timeStamp = System.currentTimeMillis();
    }



}
