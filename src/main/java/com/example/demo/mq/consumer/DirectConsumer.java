package com.example.demo.mq.consumer;

import com.example.demo.mq.entity.Mail;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
public class DirectConsumer {

    @RabbitHandler
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "directqueue2", durable = "true"),
                    exchange = @Exchange(name = "direct", durable = "true", type = "direct"),
                    key = "black"))
    public void displayMail(Mail mail, Channel channel, Message message) throws Exception {
        System.out.println("队列监听器1号收到消息" + mail.toString());
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);//如果需要确认的要调用
    }
}
