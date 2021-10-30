package com.misandev.order.consumer.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.misandev.order.consumer.config.RabbitConfig;
import com.misandev.order.consumer.entity.Order;
import com.misandev.order.consumer.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderListener {

	@Autowired
	private OrderService service;

	@RabbitListener(queues = RabbitConfig.QUEUE_NAME)
	public void receive(Order order) {
		log.info("Message received {}", order);
		Order responseId = service.save(order);
		log.info("Order saved with id {}", responseId.getId());
	}

}