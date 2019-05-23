package com.yx.log.consumer;

import com.yx.common.constants.RabbitmqQueue;
import com.yx.log.entity.Log;
import com.yx.log.service.ILogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: JST
 * @Date: 2019/5/9 11:38
 *
 * 从mq队列消费日志数据
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitmqQueue.LOG_QUEUE)//监听队列
public class LogConsumer {

    @Autowired
    private ILogService logService;

    /**
     * 处理消息
     * @param logs
     */
    @RabbitHandler
    public void handler(Log logs){
        try {
            logService.save(logs);
        }catch (Exception e){
            log.error("保存日志失败，日志 {}， 异常 {}",logs,e);
        }
    }
}
