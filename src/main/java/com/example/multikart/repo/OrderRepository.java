package com.example.multikart.repo;

import com.example.multikart.domain.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
