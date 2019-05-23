package com.yx.log.autoconfigure;

import com.yx.common.constants.RabbitmqQueue;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author: JST
 * @Date: 2019/5/10 16:22
 */
@Configuration
public class LogAutoConfig {

    /**
     * 声明队列
     * 如果日志系统已启动，或者mq上已存在队列 LogQueue.LOG_QUEUE，此处不用声明此队列<br>
     * 此处声明只是为了防止日志系统启动前，并且没有队列 LogQueue.LOG_QUEUE的情况下丢失消息
     *
     * @return
     */
    @Bean
    public Queue queue(){
        return new Queue(RabbitmqQueue.LOG_QUEUE);
    }

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Bean
    public LogMqClient logMqClient(){
        return new LogMqClient(amqpTemplate);
    }
}
