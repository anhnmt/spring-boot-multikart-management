package com.example.multikart.repo;

import com.example.multikart.domain.dto.ItemProductDTO;
import com.example.multikart.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    @Query(value = "SELECT DISTINCT new com.example.multikart.domain.dto.ItemProductDTO(p, c, pi)\n" +
            "FROM Product p\n" +
            "LEFT JOIN Category c on p.categoryId = c.categoryId\n" +
            "LEFT JOIN ProductImage pi on p.productId = pi.productId and pi.position = (Select min(position) from ProductImage where productId = p.productId)\n" +
            "WHERE p.status = :status")
    List<ItemProductDTO> findAllByStatus(Integer status);

    @Query("SELECT new com.example.multikart.domain.dto.ItemProductDTO(p, c, u, pi)\n" +
            "FROM Product p\n" +
            "LEFT JOIN Category c on p.categoryId = c.categoryId\n" +
            "LEFT JOIN Unit u on p.unitId = u.unitId\n" +
            "LEFT JOIN ProductImage pi on p.productId = pi.productId and pi.position = (Select min(position) from ProductImage where productId = p.productId)\n" +
            "WHERE p.status = :status\n" +
            "  and c.categoryId = :categoryId")
    Page<ItemProductDTO> findAllByCategoryIdAndStatus(Long categoryId, Integer status, Pageable pageable);

    @Query("SELECT new com.example.multikart.domain.dto.ItemProductDTO(p, c, u, pi)\n" +
            "FROM Product p\n" +
            "LEFT JOIN Category c on p.categoryId = c.categoryId\n" +
            "LEFT JOIN Unit u on p.unitId = u.unitId\n" +
            "LEFT JOIN ProductImage pi on p.productId = pi.productId and pi.position = (Select min(position) from ProductImage where productId = p.productId)\n" +
            "WHERE p.status = :status\n" +
            "  and c.categoryId = :categoryId")
    Page<ItemProductDTO> findPageAllByCategoryIdAndStatus(Long categoryId, Integer status, Pageable pageable);

    @Query("SELECT new com.example.multikart.domain.dto.ItemProductDTO(p, u, pi)\n" +
            "FROM Product p\n" +
            "LEFT JOIN Unit u on p.unitId = u.unitId\n" +
            "LEFT JOIN ProductImage pi on p.productId = pi.productId and pi.position = (Select min(position) from ProductImage where productId = p.productId)\n" +
            "WHERE p.status = :status\n" +
            " and (lower(p.name) like lower(concat('%', :name,'%'))\n " +
            " or lower(p.slug) like lower(concat('%', :name,'%'))\n " +
            " or lower(p.description) like lower(concat('%', :name,'%')))")
    Page<ItemProductDTO> findPageAllByNameAndStatus(String name, Integer status, Pageable pageable);

    Product findByProductIdAndStatus(Long productId, Integer status);

    @Query("SELECT new com.example.multikart.domain.dto.ItemProductDTO(p, u, pi)\n" +
            "FROM Product p\n" +
            "LEFT JOIN Unit u on p.unitId = u.unitId\n" +
            "LEFT JOIN ProductImage pi on p.productId = pi.productId and pi.position = (Select min(position) from ProductImage where productId = p.productId)\n" +
            "WHERE p.status = :status\n" +
            "  and p.productId = :productId")
    ItemProductDTO findWithImageByProductIdAndStatus(Long productId, Integer status);

    int countByNameAndStatus(String name, Integer status);

    int countByStatusIn(List<Integer> status);

    int countBySlugAndStatus(String slug, Integer status);

    @Query("SELECT new com.example.multikart.domain.dto.ItemProductDTO(p, c, u ,s, pi)\n" +
            "FROM Product p\n" +
            "LEFT JOIN Category c on p.categoryId = c.categoryId\n" +
            "LEFT JOIN Unit u on p.unitId = u.unitId\n" +
            "LEFT JOIN Supplier s on p.supplierId = s.supplierId\n" +
            "LEFT JOIN ProductImage pi on p.productId = pi.productId and pi.position = (Select min(position) from ProductImage where productId = p.productId)\n" +
            "WHERE p.status = :status\n" +
            "  and p.slug = :slug")
    ItemProductDTO findItemProductBySlugAndStatus(@Param("slug") String slug, @Param("status") Integer status);

    @Query("SELECT new com.example.multikart.domain.dto.ItemProductDTO(p, c, u, pi)\n" +
            "FROM Product p\n" +
            "LEFT JOIN Category c on p.categoryId = c.categoryId\n" +
            "LEFT JOIN Unit u on p.unitId = u.unitId\n" +
            "LEFT JOIN ProductImage pi on p.productId = pi.productId and pi.position = (Select min(position) from ProductImage where productId = p.productId)\n" +
            "WHERE p.status = :status\n" +
            "  and p.productId <> :productId")
    Page<ItemProductDTO> findRelatedByProductIdAndStatus(@Param("productId") Long productId, @Param("status") Integer status, Pageable pageable);

    @Modifying
    @Query("UPDATE Product p\n" +
            "SET p.status = :status\n" +
            "WHERE p.status <> :status\n" +
            "  and p.productId in :productIds")
    void deleteAllByProductIdInAndStatus(List<Long> productIds, Integer status);
}
