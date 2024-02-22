package com.shantesh.camelmicroservicea.route.a;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class MyFirstTimerRouter extends RouteBuilder {

    @Autowired
    private GetCurrentTimeBean getCurrentTimeBean;

    @Override
    public void configure() throws Exception {
//        queue/timer
//        transformation
//        database/log


        from("timer:first-timer")//queue
        		.log("${body}")// before transforming the body
                .transform().constant("My constant value")
                .log("${body}")// after first transforming the body
//                .transform().constant("Time is now:" + LocalDateTime.now())
                .bean(getCurrentTimeBean, "getCurrentTime1")
                .log("${body}")// after transforming the body in a bean method
                .to("log:first-timer");//database
    }


}

@Component
class GetCurrentTimeBean{
    public String getCurrentTime1(){
        return "Time is  he heheheh" + LocalDateTime.now();
    }
}
