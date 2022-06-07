package com.example.multikart.repo;

import com.example.multikart.domain.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllByStatus(Integer status);

    Product findByProductIdAndStatus(Long categoryId, Integer status);

    int countByNameAndStatus(String name, Integer status);
    int countBySlugAndStatus(String slug, Integer status);
}
