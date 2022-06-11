package com.example.multikart.repo;

import com.example.multikart.domain.dto.ItemProductDTO;
import com.example.multikart.domain.model.Category;
import com.example.multikart.domain.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllByStatus(Integer status);

    List<Product> findAllByCategoryIdAndStatus(Long categoryId, Integer status);

    Product findByProductIdAndStatus(Long productId, Integer status);

    Product findBySlugAndStatus(String slug, Integer status);

    int countByNameAndStatus(String name, Integer status);
    int countBySlugAndStatus(String slug, Integer status);

    @Query("SELECT new com.example.multikart.domain.dto.ItemProductDTO(p, c, u ,s)\n" +
            "FROM Product p\n" +
            "LEFT JOIN Category c on p.categoryId = c.categoryId\n" +
            "LEFT JOIN Unit u on p.unitId = u.unitId\n" +
            "LEFT JOIN Supplier s on p.supplierId = s.supplierId\n" +
            "WHERE p.status = :status\n" +
            "  and p.slug = :slug")
    ItemProductDTO findItemProductBySlugAndStatus(@Param("slug") String slug, @Param("status") Integer status);

    @Query("SELECT p\n" +
            "FROM Product p\n" +
            "WHERE p.status = :status\n" +
            "  and p.categoryId = :categoryId\n" +
            "  and p.productId <> :productId")
    List<Product> findRelatedByProductIdAndStatus(@Param("productId") Long productId, @Param("categoryId") Long categoryId, @Param("status") Integer status);
}
