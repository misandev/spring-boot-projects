package com.misandev.order.producer.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.misandev.order.producer.dto.ProductDto;
import com.misandev.order.producer.entity.Product;

@Component
public class ProductConverter {

	public ProductDto convertToDto(Product product) {
		var productDto = new ProductDto();
		productDto.setId(product.getId());
		productDto.setName(product.getName());
		productDto.setPrice(product.getPrice());
		productDto.setStock(product.getStock());
		return productDto;
	}

	public List<ProductDto> convertToDtoList(List<Product> products) {
		List<ProductDto> productsDto = new ArrayList<>();
		products.forEach(product -> {
			var productDto = new ProductDto();
			productDto.setId(product.getId());
			productDto.setName(product.getName());
			productDto.setPrice(product.getPrice());
			productDto.setStock(product.getStock());
			productsDto.add(productDto);
		});
		return productsDto;
	}

	public Product convertToEntity(ProductDto productDto) {
		var product = new Product();
		product.setId(productDto.getId());
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		product.setStock(productDto.getStock());
		return product;
	}

}