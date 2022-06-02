package com.example.multikart.service.impl;

import com.example.multikart.repo.OrderRepository;
import com.example.multikart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
}
