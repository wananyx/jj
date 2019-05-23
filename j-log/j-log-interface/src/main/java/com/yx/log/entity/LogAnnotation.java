package com.yx.log.entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: JST
 * @Date: 2019/5/10 15:05
 */
@Target({ElementType.METHOD})//用于描述注解的使用范围,METHOD:用于描述方法
@Retention(RetentionPolicy.RUNTIME)//注解按生命周期来划分,注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在
public @interface LogAnnotation {

    /**
     * 日志模块
     * @return
     */
    String module();

    /**
     * 记录参数
     * 尽量记录普通参数类型的方法，和能序列化的对象
     *
     * @return
     */
    boolean recordParam() default true;
}
