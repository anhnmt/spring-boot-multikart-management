package com.example.multikart.service.impl;

import com.example.multikart.repo.CustomerRepository;
import com.example.multikart.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
}
