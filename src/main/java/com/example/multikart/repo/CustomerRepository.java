package com.example.multikart.repo;

import com.example.multikart.domain.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findByEmailAndStatus(String email, Integer status);
}
