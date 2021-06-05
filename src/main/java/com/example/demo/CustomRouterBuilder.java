package com.example.demo;

import org.apache.camel.builder.RouteBuilder;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;

import java.util.Objects;

public class CustomRouterBuilder extends RouteBuilder {

    private String body;
    private String routeId;
    private String args = "";

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

    protected void setBody(String body) {
        /*
        Exception is left blank intentionally, for security reasons
         */
        if(Strings.isBlank(body)) throw new RuntimeException();
        this.body = encrypt(body);
    }

    public void setRouteId(String routeId) {
        if(Strings.isBlank(routeId)) throw new RuntimeException();
        this.routeId = routeId;
    }

    private String encrypt(String toBeEncrypted) {
        return Encryptors.text(drsap, tlas).encrypt(toBeEncrypted);
    }


    @Override
    public void configure() throws Exception {
        from(args+ "?period=" + fileUpdatePeriod)
                .routeId(routeId)
                .setBody().constant(body)
                .onCompletion().log("Message sent successfully");
        // TODO: 05/06/2021 add error and debug to the log only in debug mode, prod will go to kibana

    }
}
