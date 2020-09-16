package com.example.demo.mq.publish;

import com.example.demo.mq.entity.Mail;

public interface Publisher {

    void publishMail(Mail mail);

    void sendDirectMail(Mail mail, String routingKey);

    void sendTopicMail(String routingKey, Mail mail);
}
