package com.yx.user.exception;

/**
 * @Author: JST
 * @Date: 2019/4/28 17:32
 */
public class JException extends RuntimeException{

    private int statusCode = 400;

    public JException(int statusCode,String message) {
        super(message);
        this.statusCode = statusCode;
    }
    public JException(String message) {
        super(message);
    }

    public int getStatusCode() {
        return this.statusCode;
    }

}
