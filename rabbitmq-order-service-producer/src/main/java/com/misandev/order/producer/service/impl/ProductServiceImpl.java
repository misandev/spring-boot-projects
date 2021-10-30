package com.misandev.order.producer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.misandev.order.producer.converter.ProductConverter;
import com.misandev.order.producer.dto.ProductDto;
import com.misandev.order.producer.entity.Product;
import com.misandev.order.producer.repository.ProductRepository;
import com.misandev.order.producer.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;

	@Autowired
	private ProductConverter converter;

	@Override
	@Transactional(readOnly = true)
	public List<ProductDto> findAll() {
		List<Product> products = (List<Product>) repository.findAll();
		List<ProductDto> productsDto = new ArrayList<>();
		products.forEach(product -> productsDto.add(converter.convertToDto(product)));
		return productsDto;
	}

	@Override
	@Transactional(readOnly = true)
	public ProductDto findById(Long id) {
		return converter.convertToDto(repository.findById(id).orElse(null));
	}

	@Override
	@Transactional
	public ProductDto save(ProductDto product) {
		Product prod = repository.save(converter.convertToEntity(product));
		return converter.convertToDto(prod);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}