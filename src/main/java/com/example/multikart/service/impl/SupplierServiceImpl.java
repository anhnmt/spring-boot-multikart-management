package com.example.multikart.service.impl;

import com.example.multikart.repo.SupplierRepository;
import com.example.multikart.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;
}
