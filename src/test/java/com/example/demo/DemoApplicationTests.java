package com.example.demo;

import com.example.demo.mq.entity.Mail;
import com.example.demo.mq.producer.Producer;
import com.example.demo.mq.publish.Publisher;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {


    @Autowired
    Publisher publisher;
    @Autowired
    Producer producer;

    @Test
    void contextLoads() {
    }

    /**
     * 测试queue 模式
     */
    @Test
    void testQueue() throws InterruptedException {
        List<Mail> mails = new ArrayList<Mail>();

        for (int i = 1; i <= 5; i++) {
            mails.add(new Mail("mail_" + i, "chn_" + i, new Double(i * 10)));
        }

        for (int i = 0; i < 5; i++) {
            producer.sendMail("myqueue", mails.get(i));
        }
        Thread.sleep(1000);
    }

    @Test
    void testDirect() {
        List<Mail> mails = new ArrayList<Mail>();

        for (int i = 1; i <= 5; i++) {
            mails.add(new Mail("mail_" + i, "chn_" + i, new Double(i * 10)));
        }
        for (int i = 0; i < 5; i++) {
            publisher.sendDirectMail(mails.get(i), getRoutingKey());
        }


    }

    private String getRoutingKey() {
        String key = null;
        int rand = (int) (Math.random() * 10);
        if (rand <= 3)
            key = "orange";
        else if (rand <= 6 && rand > 3)
            key = "black";
        else
            key = "green";
        return key;
    }

    @Test
    void testTopic() {
        List<Mail> mails = new ArrayList<Mail>();

        for (int i = 1; i <= 5; i++) {
            mails.add(new Mail("mail_" + i, "chn_" + i, new Double(i * 10)));
        }
        System.out.println("mails.size() ->" + mails.size());
        for (int i = 0; i < 5; i++) {
//            publisher.sendTopicMail("*.*.rabbit", mails.get(i));
            publisher.sendTopicMail("lazy.abc", mails.get(i));
//            publisher.sendTopicMail("lazy.#", mails.get(i));
        }


    }
}
