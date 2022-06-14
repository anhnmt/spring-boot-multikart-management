package com.example.multikart.repo;

import com.example.multikart.domain.model.Product;
import com.example.multikart.domain.model.ProductImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProductImageRepository extends CrudRepository<ProductImage, Long> {

    List<ProductImage> findAllByProductIdAndStatus(Long productId, Integer status);

    int countByProductIdAndStatus(Long productId, Integer status);
}
