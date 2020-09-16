package com.example.demo.mq.entity;

public class TopicMail extends Mail {

    private static final long serialVersionUID = -5290825879021821725L;

    private String routingKey;

    public TopicMail(String mailId, String country, Double weight) {
        super(mailId, country, weight);
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    @Override
    public String toString() {
        return "TopicMail{" +
                "routingKey='" + routingKey + '\'' +
                '}';
    }
}
