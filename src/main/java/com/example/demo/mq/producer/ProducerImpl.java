package com.example.demo.mq.producer;


import com.example.demo.mq.entity.Mail;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/***
 * 消费生产者使用
 */
@Transactional
@Service("producer")
public class ProducerImpl implements Producer {

    @Autowired
    RabbitTemplate template;

    @Override
    public void sendMail(String queue, Mail mail) {
        template.setDefaultReceiveQueue(queue);
        template.convertAndSend(queue,mail);
    }
}
