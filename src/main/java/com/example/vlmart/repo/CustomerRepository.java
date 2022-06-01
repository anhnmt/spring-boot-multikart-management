package com.example.vlmart.repo;

import com.example.vlmart.domain.model.Customer;
import com.example.vlmart.domain.model.User;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findByEmailAndStatus(String email, Integer status);
}
