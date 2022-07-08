package com.example.multikart.repo;

import com.example.multikart.domain.dto.OrderDTO;
import com.example.multikart.domain.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query("SELECT new com.example.multikart.domain.dto.OrderDTO(o, c, p)\n" +
            "FROM Order o\n" +
            "LEFT JOIN Customer c on o.customerId = c.customerId\n" +
            "LEFT JOIN Payment p on o.paymentId = p.paymentId\n" +
            "WHERE o.status <> :orderStatus\n" +
            " AND p.status = :status")
    Page<OrderDTO> findAllByStatusNotAndPaginate(Integer orderStatus, Integer status, Pageable pageable);

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
            "LEFT JOIN Payment p on o.paymentId = p.paymentId\n AND p.status = :status" +
            "LEFT JOIN Transport t on o.transportId = t.transportId AND t.status = :status\n" +
            "WHERE o.status <> :orderStatus\n" +
            " AND o.customerId = :customerId\n" +
            " AND c.status = :status\n" +
            " AND o.orderId = :orderId")
    OrderDTO findByOrderIdAndCustomerIdAndStatusNot(Long orderId, Long customerId, Integer orderStatus, Integer status);

    @Query("SELECT new com.example.multikart.domain.dto.OrderDTO(o, c)\n" +
            "FROM Order o\n" +
            "LEFT JOIN Customer c on o.customerId = c.customerId\n" +
            "WHERE o.status <> :orderStatus\n" +
            " AND o.customerId = :customerId\n" +
            " AND c.status = :status")
    List<OrderDTO> findAllByCustomerIdAndStatusNot(Long customerId, Integer orderStatus, Integer status);

    int countByNameAndStatus(String name, Integer status);

    int countByCustomerIdAndStatus(Long customerId, Integer status);

    int countByCustomerIdAndStatusNot(Long customerId, Integer status);

    @Query("SELECT SUM(o.totalPrice)\n" +
            "FROM Order o\n" +
            "WHERE o.status = :status")
    int sumTotalRevenue(Integer status);

    @Modifying
    @Query("UPDATE Order o\n" +
            "SET o.status = :orderStatus\n" +
            "WHERE o.status <> :status\n" +
            "  and o.orderId = :orderId")
    void updateStatusByOrderIdAndStatusNot(Long orderId, Integer orderStatus, Integer status);
}
