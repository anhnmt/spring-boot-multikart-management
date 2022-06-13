package com.example.multikart.repo;

import com.example.multikart.domain.model.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {
    List<Payment> findAllByStatus(Integer status);

    Payment findByPaymentIdAndStatus(Long paymentId, Integer status);

    int countByNameAndStatus(String name, Integer status);
}
