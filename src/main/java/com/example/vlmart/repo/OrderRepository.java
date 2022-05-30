package com.example.vlmart.repo;

import com.example.vlmart.domain.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
