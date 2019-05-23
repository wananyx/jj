package com.yx.log.config;

import com.yx.common.constants.RabbitmqQueue;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author: JST
 * @Date: 2019/5/9 11:09
 *
 * Rabbitmq配置
 */
@Configuration
public class RabbitmqConfig {

    @Bean
    public Queue logQueue(){
        Queue queue = new Queue(RabbitmqQueue.LOG_QUEUE);
        return queue;
    }
}
