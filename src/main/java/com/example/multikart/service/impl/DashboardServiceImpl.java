package com.example.multikart.service.impl;

import com.example.multikart.common.Const;
import com.example.multikart.repo.CategoryRepository;
import com.example.multikart.repo.ProductImageRepository;
import com.example.multikart.repo.ProductRepository;
import com.example.multikart.repo.UnitRepository;
import com.example.multikart.service.DashboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Arrays;

@Service
@Slf4j
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductImageRepository productImageRepository;
    @Autowired
    private UnitRepository unitRepository;
    @Override
    public String dashboard(Model model) {
        var countProduct = productRepository.countByStatusIn(Arrays.asList(Const.DefaultStatus.ACTIVE,Const.DefaultStatus.DISABLED));
        log.info("test: "+countProduct);
        model.addAttribute("countProduct",countProduct);
        return "backend/index";
    }



}
