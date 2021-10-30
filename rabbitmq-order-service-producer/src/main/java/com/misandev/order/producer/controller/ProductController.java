package com.misandev.order.producer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.misandev.order.producer.dto.ProductDto;
import com.misandev.order.producer.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping
	public ResponseEntity<List<ProductDto>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
		var product = service.findById(id);
		if (product == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(product);
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody ProductDto product, BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			return this.validate(result);
		}
		response.put("success", service.save(product));
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody ProductDto product, BindingResult result,
			@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			return this.validate(result);
		}
		var currentProduct = this.service.findById(id);
		if (currentProduct == null) {
			return ResponseEntity.noContent().build();
		}
		currentProduct.setName(product.getName());
		currentProduct.setPrice(product.getPrice());
		currentProduct.setStock(product.getStock());
		response.put("success", service.save(currentProduct));
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	public ResponseEntity<Map<String, Object>> validate(BindingResult result) {
		Map<String, Object> errors = new HashMap<>();
		result.getFieldErrors().forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));
		return ResponseEntity.badRequest().body(errors);
	}

}