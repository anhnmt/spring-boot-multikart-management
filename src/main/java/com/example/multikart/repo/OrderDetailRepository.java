package com.example.multikart.repo;

import com.example.multikart.domain.dto.ItemProductDTO;
import com.example.multikart.domain.dto.OrderDetailDTO;
import com.example.multikart.domain.model.OrderDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail, Long> {
    @Query("SELECT new com.example.multikart.domain.dto.OrderDetailDTO(od, p, pi)\n" +
            "FROM OrderDetail od\n" +
            "LEFT JOIN Order o on od.orderId = o.orderId\n" +
            "LEFT JOIN Product p on od.productId = p.productId\n" +
            "LEFT JOIN ProductImage pi on od.productId = pi.productId\n" +
            "WHERE o.status = :status\n" +
            " AND p.status = :status\n" +
            " AND pi.status = :status\n" +
            " AND o.orderId = :orderId\n" +
            " AND od.orderId = :orderId\n" +
            "  AND pi.position = (Select min(position) from ProductImage where productId = od.productId)")
    List<OrderDetailDTO> findAllByOrderIdStatus(Long orderId, Integer status);
}
