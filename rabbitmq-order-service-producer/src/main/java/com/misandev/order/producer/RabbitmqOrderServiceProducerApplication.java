package com.misandev.order.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitmqOrderServiceProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqOrderServiceProducerApplication.class, args);
	}

}