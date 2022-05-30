package com.example.vlmart.service.impl;

import com.example.vlmart.repo.TransportRepository;
import com.example.vlmart.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransportServiceImpl implements TransportService {
    @Autowired
    private TransportRepository transportRepository;
}
