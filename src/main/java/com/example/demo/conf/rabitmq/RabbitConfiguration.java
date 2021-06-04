package com.example.demo.conf.rabitmq;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
    @Value("${rabbit.exchange.topic}")
    private String exchangeTopicName;
    @Value("${rabbit.queue.name}")
    private String queueName;
    @Value("${rabbit.routing.key}")
    private String routingKey;
    @Value("${rabbit.user}")
    private String user;
    @Value("${rabbit.pass}")
    private String pass;
    @Value("${rabbit.host}")
    private String host;
    @Value("${rabbit.port}")
    private int port;

    @Bean
    public ConnectionFactory rabbitConnectionFactory() {
        // TODO: 05/06/2021 maybe extend the connection factory for a builder implementation
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(user);
        connectionFactory.setPassword(pass);
        return connectionFactory;
    }
}
