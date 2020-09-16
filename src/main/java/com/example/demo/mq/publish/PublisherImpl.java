package com.example.demo.mq.publish;

import com.example.demo.mq.entity.Mail;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("publisher")
public class PublisherImpl implements Publisher {
    @Autowired
    RabbitTemplate rabbitTemplate;

    public void publishMail(Mail mail) {
        rabbitTemplate.convertAndSend("fanout", "", mail);
    }

    public void sendDirectMail(Mail mail, String routingKey) {
        rabbitTemplate.convertAndSend("direct", routingKey, mail);
    }

    @Override
    public void sendTopicMail(String routingKey, Mail mail) {
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(mail.getMailId());
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.convertAndSend("myTopic", routingKey, mail);

//        rabbitTemplate.convertAndSend("myTopic", // exchange
//                routingKey,//路由键
//                mail, //消息对象
//                correlationData); //消息的唯一标识
    }

    final ConfirmCallback confirmCallback = new ConfirmCallback() {

        //回调函数： confirm 确认
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {

            System.out.println("ack confirming ....");
            String id = correlationData.getId();
            if (ack) {
                System.out.println("confirm success...>" + id);
            } else {
                System.out.println("confirm failure... >" + id + ":" + cause);
            }
        }
    };
}
