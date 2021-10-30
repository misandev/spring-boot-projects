package com.misandev.order.producer.service;

import java.util.List;

import com.misandev.order.producer.dto.ProductDto;

public interface ProductService {

	List<ProductDto> findAll();

	ProductDto findById(Long id);

	ProductDto save(ProductDto product);

	void deleteById(Long id);

}