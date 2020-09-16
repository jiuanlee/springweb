package com.example.demo.mq.consumer;

import com.example.demo.mq.entity.Mail;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class TopicConsumer {

    @RabbitHandler
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "topic1", durable = "true"),
                    exchange = @Exchange(name = "myTopic",type = "topic"),
                    key = "lazy.#"))
    public void consumerTopic(@Payload Mail mail, Channel channel, Message message) throws Exception {
        System.out.println(mail.getMailId());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

//    public void consumerTopic(@Payload Mail mail, Channel channel, Message message) throws Exception {
//        System.out.println(mail.getMailId());
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//    }
}
