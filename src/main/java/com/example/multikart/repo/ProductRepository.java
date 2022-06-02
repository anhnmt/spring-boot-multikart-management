package com.example.multikart.repo;

import com.example.multikart.domain.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
