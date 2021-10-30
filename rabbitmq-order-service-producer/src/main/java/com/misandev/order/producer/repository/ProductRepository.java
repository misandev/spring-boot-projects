package com.misandev.order.producer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.misandev.order.producer.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

	@Query("select p from Product p where p.name like %?1% and p.stock != 0")
	List<Product> findByName(String name);

}