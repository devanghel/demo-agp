package com.example.demo.conf.rabitmq;

import com.example.demo.model.bid.Bids;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.base.HttpOperationFailedException;

import java.util.List;

import static org.apache.camel.Exchange.EXCEPTION_CAUGHT;
import static org.apache.camel.LoggingLevel.WARN;

public class ExceptionHandlingRoute extends RouteBuilder {

    @Override
    public void configure() {

        //Default Error handler
        onException(Exception.class)
                .log("This is error message: ${exception}");

        //Custom OnException
        onException(HttpOperationFailedException.class)
                .log("Http Request Failed, should retry!")
                .maximumRedeliveries(3)
                .maximumRedeliveryDelay(1000)
                .retryAttemptedLogLevel(WARN);

        //Try Catch Finally
        from("direct:tryCatch")
                .doTry()
                .process(exchange -> log.info("Try to process something"))
                .doCatch(Exception.class)
                .to("mock:error")
                .doFinally()
                .to("mock:end");

        //Handled/Continued
        from("direct:start")
                .process(exchange -> log.info("Throw some exception"))
                .to("mock:end");

        //send the stack trace back
        onException(/*Unhandled exception*/)
                .handled(false)//Default
                .log("error sent back to the client");

        //send a readable error message back and handle error internally
        onException(/*HandledException*/)
                .handled(true)
                .setBody(constant("error"))
                .to("mock:error");

        //ignore the exception and continue the route
        onException(/*Some Exception that we want to ignore*/)
                .continued(true);

        //Use processor for more control
        onException(Exception.class)
                .handled(true)
                .process(exchange ->  {
                    String exception = exchange.getProperty(EXCEPTION_CAUGHT, String.class);
                    log.info("Exception {}", exception);
                    //email, reroute, etc.
                });

    }

    public static class BeanValidationRoute extends RouteBuilder {

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
}
