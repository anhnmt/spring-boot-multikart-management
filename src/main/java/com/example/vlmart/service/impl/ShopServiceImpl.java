package com.example.vlmart.service.impl;

import com.example.vlmart.repo.ShopRepository;
import com.example.vlmart.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopRepository shopRepository;
}
