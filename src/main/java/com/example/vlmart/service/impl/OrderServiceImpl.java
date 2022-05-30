package com.example.vlmart.service.impl;

import com.example.vlmart.repo.OrderRepository;
import com.example.vlmart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
}
