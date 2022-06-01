package com.example.multikart.service.impl;

import com.example.multikart.repo.UnitRepository;
import com.example.multikart.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitServiceImpl implements UnitService {
    @Autowired
    private UnitRepository unitRepository;
}
