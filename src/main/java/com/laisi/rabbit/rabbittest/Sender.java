package com.laisi.rabbit.rabbittest;

/**
 * @author sunlei
 * @date 2019/4/1
 */
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    private final AmqpTemplate amqpTemplate;

    @Autowired
    public Sender(AmqpAdmin amqpAdmin, AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendMsg(){
        // Topic
        amqpTemplate.convertAndSend("hello-router", "hello_routing_key", "hello");
    };

}