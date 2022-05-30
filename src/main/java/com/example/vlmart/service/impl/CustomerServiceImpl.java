package com.example.vlmart.service.impl;

import com.example.vlmart.repo.CustomerRepository;
import com.example.vlmart.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
}
