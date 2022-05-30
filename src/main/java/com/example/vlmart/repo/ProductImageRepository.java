package com.example.vlmart.repo;

import com.example.vlmart.domain.model.ProductImage;
import org.springframework.data.repository.CrudRepository;

public interface ProductImageRepository extends CrudRepository<ProductImage, Long> {
}
