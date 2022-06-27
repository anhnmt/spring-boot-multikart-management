package com.example.multikart.repo;

import com.example.multikart.domain.model.ProductImage;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProductImageRepository extends CrudRepository<ProductImage, Long> {

    ProductImage findByProductImageIdAndStatus(Long productImageId, Integer status);

    List<ProductImage> findAllByProductIdAndStatusOrderByPositionAsc(Long productId, Integer status);

    int countByProductIdAndStatus(Long productId, Integer status);

    @Query(value = "Select position from product_images where product_id = :productId ORDER BY position desc, updated_at desc ASC LIMIT 1", nativeQuery = true)
    int findMinPositionByProductId(@Param("productId") Long productId);

    @Query("Select min(position) from ProductImage where productId = :productId and status = :status")
    Integer findMinPositionByProductIdAndStatus(@Param("productId") Long productId, @Param("status") Integer status);

    @Query("Select max(position) from ProductImage where productId = :productId and status = :status")
    Integer findMaxPositionByProductIdAndStatus(@Param("productId") Long productId, @Param("status") Integer status);

    @Modifying
    @Query("UPDATE ProductImage \n" +
            "SET position = position + 1\n" +
            "WHERE productId = :productId\n" +
            " AND position = :position - 1\n" +
            " AND productImageId <> :productImageId\n" +
            " AND status = :status")
    void updatePositionUp(@Param("productId") Long productId, @Param("productImageId") Long productImageId, @Param("position") Integer position, @Param("status") Integer status);

    @Modifying
    @Query("UPDATE ProductImage \n" +
            "SET position = position - 1\n" +
            "WHERE productId = :productId\n" +
            " AND position = :position + 1\n" +
            " AND productImageId <> :productImageId\n" +
            " AND status = :status")
    void updatePositionDown(@Param("productId") Long productId, @Param("productImageId") Long productImageId, @Param("position") Integer position, @Param("status") Integer status);

    @Modifying
    @Query("UPDATE ProductImage \n" +
            "SET position = position - 1\n" +
            "WHERE productId = :productId\n" +
            " AND position > :position\n" +
            " AND productImageId <> :productImageId\n" +
            " AND status = :status")
    void updatePositionDelete(@Param("productId") Long productId, @Param("productImageId") Long productImageId, @Param("position") Integer position, @Param("status") Integer status);

    @Modifying
    @Query("UPDATE ProductImage pi\n" +
            "SET pi.status = :status\n" +
            "WHERE pi.status <> :status\n" +
            "  and pi.productId in :productIds")
    void deleteAllByProductIdInAndStatus(List<Long> productIds, Integer status);
}
