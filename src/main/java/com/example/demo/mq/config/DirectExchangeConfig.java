package com.example.demo.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectExchangeConfig {
    @Bean
    public DirectExchange directExchange() {
        DirectExchange directExchange = new DirectExchange("direct");
        return directExchange;
    }

    @Bean
    public Queue directQueue1() {
        Queue queue = new Queue("directqueue1");
        return queue;
    }

    @Bean
    public Queue directQueue2() {
        Queue queue = new Queue("directqueue2");
        return queue;
    }

    //3个binding将交换机和相应队列连起来
    @Bean
    public Binding bindingOrange() {
        Binding binding = BindingBuilder.bind(directQueue1()).to(directExchange()).with("orange");
        return binding;
    }

    @Bean
    public Binding bindingBlack() {
        Binding binding = BindingBuilder.bind(directQueue2()).to(directExchange()).with("black");
        return binding;
    }

    @Bean
    public Binding bindingGreen() {
        Binding binding = BindingBuilder.bind(directQueue2()).to(directExchange()).with("green");
        return binding;
    }

}
