package com.yx.log.autoconfigure;

import com.alibaba.fastjson.JSONObject;
import com.yx.common.constants.RabbitmqQueue;
import com.yx.common.utils.SecurityUtils;
import com.yx.log.entity.Log;
import com.yx.log.entity.LogAnnotation;
import com.yx.user.entity.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

/**
 * @Author: JST
 * @Date: 2019/5/10 15:13
 *
 * aop实现记录日志
 */
@Slf4j
@Aspect
public class LogAop {

    /**
     * 注入rabbitmq
     */
    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 对带@LogAnnotation注解的方法使用aop获取方法执行前后的日志
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("@annotation(com.yx.log.entity.LogAnnotation)")
    public Object logSave(ProceedingJoinPoint joinPoint) throws Throwable{
        Log logs = new Log();
        try {
            //设置日志时间
            logs.setCreated(new Date());

            //设置日志操作人
            LoginUser loginUser = SecurityUtils.getLoginUser();
            if (loginUser != null) {
                logs.setUsername(loginUser.getUsername());
            }

            //设置日志模块
            //获取目标对象的方法对象(包括方法上的注解)
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            //获取注解为@LogAnnotation的注解对象
            LogAnnotation annotation = method.getDeclaredAnnotation(LogAnnotation.class);
            logs.setModule(annotation.module());

            //设置日志参数
            if (annotation.recordParam()) {//为true表示需要获取参数信息
                //获取参数名数组
                String[] parameterNames = signature.getParameterNames();
                if (parameterNames != null && parameterNames.length > 0) {
                    //获取参数数组
                    Object[] args = joinPoint.getArgs();
                    HashMap<String, Object> params = new HashMap<>();
                    for (int i = 0; i < parameterNames.length; i++) {
                        Object value = args[i];
                        if (value instanceof Serializable) {
                            params.put(parameterNames[i], value);
                        }
                    }
                    try {
                        logs.setParams(JSONObject.toJSONString(params));// 以json的形式记录参数
                    } catch (Exception e) {
                        log.error("记录参数错误: {}", e.getMessage());
                    }
                }
            }

            //执行原方法
            Object proceed = joinPoint.proceed();
            //记录方法执行成功
            logs.setFlag(Boolean.TRUE);
            return proceed;
        } catch (Exception e2) {
            //记录方法执行失败
            logs.setFlag(Boolean.TRUE);
            //记录方法执行失败原因
            logs.setRemark(e2.getMessage());
            throw e2;
        } finally {
            //异步将日志信息发送到队列

            CompletableFuture.runAsync(() -> {
                amqpTemplate.convertAndSend(RabbitmqQueue.LOG_QUEUE, logs);
                log.info("发送日志到队列：{}", logs);
            });
        }
    }
}
