package com.shantesh.camelmicroservicea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CamelMicroserviceAApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamelMicroserviceAApplication.class, args);
		System.out.println("Learning camel routing, from and to to endpoints, timer-to-log, fileyafolder-to- fileyafolder, queue-to-database");
	}

}
