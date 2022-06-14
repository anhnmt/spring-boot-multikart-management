package com.example.multikart.repo;

import com.example.multikart.domain.model.Product;
import com.example.multikart.domain.model.ProductImage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProductImageRepository extends CrudRepository<ProductImage, Long> {

    List<ProductImage> findAllByProductIdAndStatus(Long productId, Integer status);

    int countByProductIdAndStatus(Long productId, Integer status);

    @Query("SELECT pi\n" +
            "FROM ProductImage pi\n" +
            "WHERE pi.status = :status\n" +
            "  and pi.productId = :productId\n" +
            "  and pi.position = (Select min(position) from ProductImage where productId = :productId)")
    ProductImage findFirstByProductIdAndStatus(@Param("productId") Long productId, @Param("status") Integer status);
}
