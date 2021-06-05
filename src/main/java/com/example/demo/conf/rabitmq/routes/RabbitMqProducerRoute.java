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

    @Value("${drsap}")
    private String drsap;

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
                .setBody().constant(encrypt(body))
                .onCompletion().log("was sent succesefully");
                // TODO: 05/06/2021 add error and debug to the log only in debug mode, prod will go to kibana
    }

    private String encrypt(String toBeEncrypted) {
        return Encryptors.text(drsap, tlas).encrypt(toBeEncrypted);
    }
}
