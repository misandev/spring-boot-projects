package com.misandev.order.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitmqOrderServiceConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqOrderServiceConsumerApplication.class, args);
	}

}