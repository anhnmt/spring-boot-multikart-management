package com.example.multikart.repo;

import com.example.multikart.domain.model.ProductImage;
import org.springframework.data.repository.CrudRepository;

public interface ProductImageRepository extends CrudRepository<ProductImage, Long> {
}
