package com.example.multikart.repo;

import com.example.multikart.domain.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findAllByStatus(Integer status);

    Customer findByCustomerIdAndStatus(Long customerId, Integer status);

    Customer findByEmailAndStatus(String email, Integer status);

    int countByEmailAndStatus(String email, Integer status);
}
