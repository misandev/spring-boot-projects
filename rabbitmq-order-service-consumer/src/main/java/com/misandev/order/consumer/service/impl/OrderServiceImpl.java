package com.misandev.order.consumer.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.misandev.order.consumer.dto.ProductDto;
import com.misandev.order.consumer.entity.Order;
import com.misandev.order.consumer.repository.OrderRepository;
import com.misandev.order.consumer.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private RestTemplate client;

	@Autowired
	private OrderRepository repository;

	private static final String URL = "http://order-service-producer:8082/api/products";

	@Override
	@Transactional
	public Order save(Order order) {
		order.getItems().forEach(item -> {
			ProductDto product = findProductById(item.getProductId());
			product.setStock(product.getStock() - item.getQuantity());
			updateProductStock(product);
		});
		return repository.save(order);
	}

	@Transactional(readOnly = true)
	public ProductDto findProductById(Long id) {
		Map<String, String> productId = new HashMap<>();
		productId.put("id", id.toString());
		return client.getForObject(URL + "/{id}", ProductDto.class, productId);
	}

	@Transactional
	public ProductDto updateProductStock(ProductDto product) {
		HttpEntity<ProductDto> body = new HttpEntity<>(product);
		ResponseEntity<ProductDto> response = client.exchange(URL, HttpMethod.POST, body, ProductDto.class);
		return response.getBody();
	}

}