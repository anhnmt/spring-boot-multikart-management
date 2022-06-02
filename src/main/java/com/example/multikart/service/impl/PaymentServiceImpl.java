package com.example.multikart.service.impl;

import com.example.multikart.repo.PaymentRepository;
import com.example.multikart.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
}
