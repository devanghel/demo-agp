package com.example.demo.conf.rabitmq.routes;

import org.apache.camel.builder.RouteBuilder;

public class RabbitMqProducerRoute extends RouteBuilder {

    @Override
    public void configure() {

        from("timer:foo?period=100000")
                .routeId("RabbitMqProducer")
                .setBody().constant("{\"some\":\"field\"}")
                .log("Trying to send : ${body}")
                .to("rabbitmq:testExchange?autoDelete=false&declare=false&connectionFactory=#rabbitConnectionFactory")
                .log("Message Sent!");

    }
}
