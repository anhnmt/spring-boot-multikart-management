package com.example.multikart.service.impl;

import com.example.multikart.repo.TransportRepository;
import com.example.multikart.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransportServiceImpl implements TransportService {
    @Autowired
    private TransportRepository transportRepository;
}
