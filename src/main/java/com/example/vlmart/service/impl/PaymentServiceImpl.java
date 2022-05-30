package com.example.vlmart.service.impl;

import com.example.vlmart.repo.PaymentRepository;
import com.example.vlmart.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
}
