package com.example.multikart.repo;

import com.example.multikart.domain.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findAllByStatus(Integer status);

    Customer findByCustomerIdAndStatus(Long userId, Integer status);

    Customer findByEmailAndStatus(String email, Integer status);

    int countByEmailAndStatus(String email, Integer status);
}
