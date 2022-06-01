package com.example.multikart.service.impl;

import com.example.multikart.repo.OrderDetailRepository;
import com.example.multikart.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
}
