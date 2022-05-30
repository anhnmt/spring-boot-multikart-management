package com.example.vlmart.service.impl;

import com.example.vlmart.repo.UnitRepository;
import com.example.vlmart.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitServiceImpl implements UnitService {
    @Autowired
    private UnitRepository unitRepository;
}
