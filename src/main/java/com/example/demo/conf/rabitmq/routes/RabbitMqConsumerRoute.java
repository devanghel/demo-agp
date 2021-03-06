package com.example.demo.conf.rabitmq.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqConsumerRoute extends RouteBuilder {

    @Override
    public void configure() {
        from("rabbitmq:testExchange?queue=testQueue&autoDelete=false&declare=false&connectionFactory=#rabbitConnectionFactory" )
                .routeId("ZU")
                .routeId("AQ")
                .log("Received body: ${body}" );
    }
}
