package com.laisi.rabbit.rabbittest;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sunlei
 * @date 2019/4/1
 */
@Configuration
public class RabbitConfig {
    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("hello-router");
    }

    @Bean
    Binding bindingExchangeMessage(Queue helloQueue, TopicExchange exchange) {
        // 将队列helloQueue绑定到名为hello_routing_key的routingKey
        return BindingBuilder.bind(helloQueue).to(exchange).with("hello_routing_key");
    }

}
