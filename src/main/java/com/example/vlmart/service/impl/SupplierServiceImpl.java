package com.example.vlmart.service.impl;

import com.example.vlmart.repo.SupplierRepository;
import com.example.vlmart.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;
}
