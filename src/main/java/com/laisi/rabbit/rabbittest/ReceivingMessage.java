package com.laisi.rabbit.rabbittest;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * @author sunlei
 * @date 2019/4/1
 */
@Component
public class ReceivingMessage {

    @RabbitListener(queues = "hello")
    @RabbitHandler
    public void process2(String message) {
        System.out.println("queue:topic.hello,message:" + message);
    }

}
