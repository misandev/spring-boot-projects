package com.misandev.order.consumer.repository;

import org.springframework.data.repository.CrudRepository;

import com.misandev.order.consumer.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}