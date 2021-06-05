package com.example.demo.conf.rabitmq;

import com.example.demo.conf.rabitmq.routes.RabbitMqProducerRoute;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

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
        // todo check if you need to provide, connection factory with beans, or it will discover it automatically
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(user);
        connectionFactory.setPassword(pass);
        return connectionFactory;
    }

    @Bean
    @Scope(scopeName = "singleton")
    public RabbitMqProducerRoute getRabbitMqProducerRoute() {
        return new RabbitMqProducerRoute();
    }
}
