package com.example.vlmart.service.impl;

import com.example.vlmart.repo.OrderDetailRepository;
import com.example.vlmart.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
}
