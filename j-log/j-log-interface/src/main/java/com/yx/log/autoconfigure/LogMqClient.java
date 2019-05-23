package com.yx.log.autoconfigure;

import com.yx.common.constants.RabbitmqQueue;
import com.yx.common.utils.SecurityUtils;
import com.yx.log.entity.Log;
import com.yx.user.entity.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

/**
 * @Author: JST
 * @Date: 2019/5/10 16:09
 * 将日志存储到rabbitmq队列中，用于代替logService.save(log)方法
 * 在LogAutoConfiguration中将该类声明成Bean，使用时直接使用@Autowired注入即可
 */
@Slf4j
public class LogMqClient {

    private AmqpTemplate amqpTemplate;

    public LogMqClient(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendLogMsg(String module, String username, String params, String remark, boolean flag) {
        CompletableFuture.runAsync(() -> {
            try {
                Log logs = new Log();
                //设置日志时间
                logs.setCreated(new Date());
                //设置日志操作人
                if (StringUtils.isNotBlank(username)) {
                    logs.setUsername(username);
                } else {
                    LoginUser loginUser = SecurityUtils.getLoginUser();
                    if (loginUser != null) {
                        logs.setUsername(loginUser.getUsername());
                    }
                }
                //设置日志其他属性
                logs.setFlag(flag);
                logs.setModule(module);
                logs.setParams(params);
                logs.setRemark(remark);

                amqpTemplate.convertAndSend(RabbitmqQueue.LOG_QUEUE,logs);
                log.info("发送日志到队列：{}", logs);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

    }
}
