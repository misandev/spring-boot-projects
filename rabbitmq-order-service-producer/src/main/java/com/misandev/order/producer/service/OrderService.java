package com.misandev.order.producer.service;

import java.util.List;

import com.misandev.order.producer.dto.OrderDto;
import com.misandev.order.producer.dto.ProductDto;

public interface OrderService {

	String send(OrderDto order);

	List<ProductDto> findProductByName(String name);

}