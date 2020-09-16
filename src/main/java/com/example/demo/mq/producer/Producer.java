package com.example.demo.mq.producer;


import com.example.demo.mq.entity.Mail;

public interface Producer {

    /**
     * 发送邮件接口。
     *
     * @param queue 队列标示，not Null。
     * @param mail   邮件消息。
     */
    void sendMail(String queue, Mail mail);
}
