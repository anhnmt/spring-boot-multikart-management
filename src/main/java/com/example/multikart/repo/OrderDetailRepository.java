package com.example.multikart.repo;

import com.example.multikart.domain.model.OrderDetail;
import org.springframework.data.repository.CrudRepository;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, Long> {
}
