package com.example.multikart.repo;

import com.example.multikart.domain.model.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAllByStatus(Integer status);

    Order findByOrderIdAndStatus(Long categoryId, Integer status);

    int countByNameAndStatus(String name, Integer status);
}
