package com.example.multikart.repo;

import com.example.multikart.domain.model.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
