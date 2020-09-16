package com.example.demo.mq.config;

/**
 * 通过javaConfig配置rabbit
 */

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

//@Configuration
//@EnableRabbit
public class RabbitConfig {

    public ConnectionFactory getRabbitCtFactory() {
        CachingConnectionFactory ctFactory = new CachingConnectionFactory("localhost");
        ctFactory.setPort(5672);
        ctFactory.setUsername("guest");
        ctFactory.setPassword("guest");
        ctFactory.setVirtualHost("/testmq");
        return ctFactory;
    }


}
