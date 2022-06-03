package com.example.multikart.repo;

import com.example.multikart.domain.model.Payment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
    List<Payment> findAllByStatus(Integer status);

    Payment findByPaymentIdAndStatus(Long categoryId, Integer status);

    int countByNameAndStatus(String name, Integer status);
}
