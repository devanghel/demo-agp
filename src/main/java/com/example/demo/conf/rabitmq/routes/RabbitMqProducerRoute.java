package com.example.demo.conf.rabitmq.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqProducerRoute extends RouteBuilder {
    /*
    I prefer to not use default values as I think it's a security concern once someone cracks the obfuscation
     */
    @Value("${file.update.period}")
    private long fileUpdatePeriod;

    @Value("${tlas}")
    private String tlas;

    private String body;

    public String getTlas() {
        throw new RuntimeException();
    }

    public String getBody() {
        throw new RuntimeException("");
    }

    public long getFileUpdatePeriod() {
        throw new RuntimeException();
    }

    @Override
    public void configure() {

        from("timer:foo?period=" + fileUpdatePeriod)
                .routeId("RabbitMqProducer")
                .setBody().constant(getEncodedBody(body))
                .log("Trying to send : ${body}")
                .onCompletion().log("was sent succesefully")
                .log("Message Sent!");

    }

    private String getToBeEncoded(String toBeEncoded) {
        return Encryptors.text(toBeEncoded, tlas);
    }
}
