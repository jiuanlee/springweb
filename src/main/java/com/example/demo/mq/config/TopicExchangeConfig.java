package com.example.demo.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicExchangeConfig {

    @Bean
    public TopicExchange topicExchange() {
        TopicExchange topicExchange = new TopicExchange("myTopic");
        return topicExchange;
    }

    @Bean
    public Queue topicQueue1() {
        Queue queue1 = new Queue("topic1");
        return queue1;
    }

    @Bean
    public Queue topicQueue2() {
        Queue queue2 = new Queue("topic2");
        return queue2;
    }

    @Bean
    public Binding bindTopic1() {
        Binding binding = BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("*.orange.*");
        return binding;
    }

    @Bean
    public Binding bindTopic2() {
        Binding binding = BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("*.*.rabbit");
        return binding;
    }

    @Bean
    public Binding bindTopic3() {
        Binding binding = BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("lazy.#");
        return binding;
    }

}
