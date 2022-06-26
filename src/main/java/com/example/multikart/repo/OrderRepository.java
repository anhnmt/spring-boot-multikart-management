package com.example.multikart.repo;

import com.example.multikart.domain.dto.OrderDTO;
import com.example.multikart.domain.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface OrderRepository extends CrudRepository<Order, Long> {
    @Query("SELECT new com.example.multikart.domain.dto.OrderDTO(o, c, p, t)\n" +
            "FROM Order o\n" +
            "LEFT JOIN Customer c on o.customerId = c.customerId\n" +
            "LEFT JOIN Payment p on o.paymentId = p.paymentId\n" +
            "LEFT JOIN Transport t on o.transportId = t.transportId\n" +
            "WHERE o.status <> :orderStatus\n" +
            " AND p.status = :status\n" +
            " AND t.status = :status")
    List<OrderDTO> findAllByStatusNot(Integer orderStatus, Integer status);

    @Query("SELECT new com.example.multikart.domain.dto.OrderDTO(o, c, p, t)\n" +
            "FROM Order o\n" +
            "LEFT JOIN Customer c on o.customerId = c.customerId\n" +
            "LEFT JOIN Payment p on o.paymentId = p.paymentId\n" +
            "LEFT JOIN Transport t on o.transportId = t.transportId\n" +
            "WHERE o.status <> :orderStatus\n" +
            " AND p.status = :status\n" +
            " AND t.status = :status\n" +
            " AND o.orderId = :orderId")
    OrderDTO findByOrderIdAndStatusNot(Long orderId, Integer orderStatus, Integer status);

    @Query("SELECT new com.example.multikart.domain.dto.OrderDTO(o, c, p, t)\n" +
            "FROM Order o\n" +
            "LEFT JOIN Customer c on o.customerId = c.customerId\n" +
            "LEFT JOIN Payment p on o.paymentId = p.paymentId\n" +
            "LEFT JOIN Transport t on o.transportId = t.transportId\n" +
            "WHERE o.status <> :status\n" +
            " AND o.customerId = :customerId\n" +
            " AND c.status <> :status\n" +
            " AND p.status <> :status\n" +
            " AND t.status <> :status\n" +
            " AND o.orderId = :orderId")
    OrderDTO findByOrderIdAndCustomerIdAndStatusNot(Long orderId, Long customerId, Integer status);

    @Query("SELECT new com.example.multikart.domain.dto.OrderDTO(o, c)\n" +
            "FROM Order o\n" +
            "LEFT JOIN Customer c on o.customerId = c.customerId\n" +
            "WHERE o.status <> :status\n" +
            " AND o.customerId = :customerId\n" +
            " AND c.status <> :status")
    List<OrderDTO> findAllByCustomerIdAndStatusNot(Long customerId, Integer status);

    int countByNameAndStatus(String name, Integer status);
}
