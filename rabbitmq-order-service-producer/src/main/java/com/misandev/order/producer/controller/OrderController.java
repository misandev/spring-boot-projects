package com.misandev.order.producer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.misandev.order.producer.dto.OrderDto;
import com.misandev.order.producer.dto.ProductDto;
import com.misandev.order.producer.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService service;

	@GetMapping("/filter-products/{name}")
	public ResponseEntity<List<ProductDto>> findProductByName(@PathVariable String name) {
		return ResponseEntity.ok(service.findProductByName(name));
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> send(@Valid @RequestBody OrderDto order, BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			return this.validate(result);
		}
		response.put("message", service.send(order));
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	private ResponseEntity<Map<String, Object>> validate(BindingResult result) {
		Map<String, Object> errors = new HashMap<>();
		result.getFieldErrors().forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));
		return ResponseEntity.badRequest().body(errors);
	}

}