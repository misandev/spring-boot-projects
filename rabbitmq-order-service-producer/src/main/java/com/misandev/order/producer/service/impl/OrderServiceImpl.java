package com.misandev.order.producer.service.impl;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.misandev.order.producer.config.RabbitConfig;
import com.misandev.order.producer.converter.ProductConverter;
import com.misandev.order.producer.dto.OrderDto;
import com.misandev.order.producer.dto.ProductDto;
import com.misandev.order.producer.repository.ProductRepository;
import com.misandev.order.producer.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private ProductRepository repository;

	@Autowired
	private ProductConverter converter;

	@Override
	public String send(OrderDto order) {
		template.convertAndSend(RabbitConfig.TOPIC_EXCHANGE_NAME, RabbitConfig.ROUTING_KEY_NAME, order);
		log.info("Message sent {}", order);
		return "Sent!";
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductDto> findProductByName(String name) {
		return converter.convertToDtoList(repository.findByName(name));
	}

}