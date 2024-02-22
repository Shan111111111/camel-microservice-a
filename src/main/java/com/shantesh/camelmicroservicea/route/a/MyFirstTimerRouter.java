package com.shantesh.camelmicroservicea.route.a;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class MyFirstTimerRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
//        queue/timer
//        transformation
//        database/log


        from("timer:first-timer")//queue
                .to("log:first-timer");//database
    }

}
