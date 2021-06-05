package com.example.demo.conf.rabitmq.routes;

import lombok.SneakyThrows;
import org.apache.camel.builder.RouteBuilder;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;

import javax.management.BadAttributeValueExpException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CustomProducerRouterBuilder extends RouteBuilder {

    private String body;
    private String args = "";
    private List<String> queues;
    /*
I prefer to not use default values as I think it's a security concern once someone cracks the obfuscation
 */
    @Value("${file.update.period}")
    private Long fileUpdatePeriod;

    @Value("${tlas}")
    private String tlas;

    @Value("${drsap}")
    private String drsap;

    @Value("${file.max.update.period}")
    private Long maxUpdatePeriod;

    @SneakyThrows
    protected void setBody(String body) {
        /*
        Exception is left blank intentionally, for security reasons
         */
        if(Strings.isBlank(body)) throw new BadAttributeValueExpException("");
        this.body = encrypt(body);
    }

    @SneakyThrows
    public void setQueues(String[] queues) {
        if(Objects.isNull(queues) || queues.length == 0) throw new BadAttributeValueExpException("");
        this.queues = Arrays.asList(queues);
    }

    @SneakyThrows
    public void setArgs(String args) {
        if(Objects.isNull(args)) throw new BadAttributeValueExpException("");
        this.args = args;
    }

    private String encrypt(String toBeEncrypted) {
        return Encryptors.text(drsap, tlas).encrypt(toBeEncrypted);
    }

    // TODO: 05/06/2021 sanitize args
    @Override
    public void configure() throws Exception {
        queues.forEach(it -> {
            from("timer:"+ it +"?period=" + fileUpdatePeriod + args)
                    .routeId(it)
                    .setBody().constant(body)
                    .onCompletion().log("Message sent successfully")
                    .to("rabbitmq:testExchange?connectionFactory=#rabbitConnectionFactory");
        });

        // TODO: 05/06/2021 add error and debug to the log only in debug mode, prod will go to kibana

    }
}
