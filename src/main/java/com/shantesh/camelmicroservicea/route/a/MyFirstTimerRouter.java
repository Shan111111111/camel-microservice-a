package com.shantesh.camelmicroservicea.route.a;

import java.time.LocalDateTime;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyFirstTimerRouter extends RouteBuilder {

    @Autowired
    private GetCurrentTimeBean getCurrentTimeBean;
    
	@Autowired
	private SimpleLoggingProcessingComponent simpleLoggingProcessingComponent;

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
                
                .bean(simpleLoggingProcessingComponent)
				.log("${body}")// after processing the body (not transforming get it clear) in a bean method
				
				.process(new SimpleLoggingProcessor())
				.log("${body}")// after processing the body (not transforming get it clear) in a bean method
				
                .to("log:first-timer");//database
    }


}

@Component
class GetCurrentTimeBean{
    public String getCurrentTime1(){
        return "Time is  he heheheh" + LocalDateTime.now();
    }
}

@Component
class SimpleLoggingProcessingComponent {
	private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);

	public void process(String message) {
		logger.info("SimpleLoggingProcessingComponent--->---> {}", message);
	}
}

class SimpleLoggingProcessor implements Processor {
	private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessor.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		logger.info("SimpleLoggingProcessor---> {}", exchange.getMessage().getBody());
	}

	
}