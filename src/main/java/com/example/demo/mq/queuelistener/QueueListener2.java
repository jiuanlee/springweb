package com.example.demo.mq.queuelistener;

import com.example.demo.mq.entity.Mail;

//@Component
public class QueueListener2 {

//    @RabbitListener(queues = "myqueue")
    public void displayMail(Mail mail) throws Exception {
        System.out.println("队列监听器2号收到消息"+mail.toString());
    }
}
