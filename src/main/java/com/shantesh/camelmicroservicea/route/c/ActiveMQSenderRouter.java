package com.shantesh.camelmicroservicea.route.c;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQSenderRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("timer:active-mq-timer?period=10000")
		.log("${body}")
		.transform().constant("My message for Mq")
		.log("${body}")
		.to("activemq:my-activemq-queue");

	}
}
