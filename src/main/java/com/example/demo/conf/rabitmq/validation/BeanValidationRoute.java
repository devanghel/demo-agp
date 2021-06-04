package com.example.demo.conf.rabitmq.validation;

import com.example.demo.model.bid.Bids;
import org.apache.camel.builder.RouteBuilder;

import java.util.List;

public class BeanValidationRoute extends RouteBuilder {

    @Override
    public void configure() {

        Bids.Bid bid = Bids.Bid.builder()
                .id("290092888459")
                .ts("1992388288")
                .ty("ZU")
                .pl("Q29uZmlybWVkOiAzOTI")
                .build();

        from("timer:foo?period=100000")
                .setBody().constant(List.of(bid))
                .to("bean-validator:validateBid");

    }
}
