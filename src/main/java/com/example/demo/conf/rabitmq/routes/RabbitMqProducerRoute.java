package com.example.demo.conf.rabitmq.routes;

import org.springframework.beans.factory.annotation.Value;

public final class RabbitMqProducerRoute extends CustomProducerRouterBuilder {
    @Value("${rabbit.queues}")
    private String[] queues;
    @Override
    public void setBody(String body) {
        super.setBody(body);
    }

    @Override
    public void setQueues(String[] queues) {
        super.setQueues(queues);
    }
}
