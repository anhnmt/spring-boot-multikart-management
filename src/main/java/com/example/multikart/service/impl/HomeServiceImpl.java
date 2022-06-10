package com.example.multikart.service.impl;

import com.example.multikart.common.Const.DefaultStatus;
import com.example.multikart.domain.dto.CategoryProductDTO;
import com.example.multikart.domain.dto.ItemProductDTO;
import com.example.multikart.repo.CategoryRepository;
import com.example.multikart.repo.ProductRepository;
import com.example.multikart.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public String home(Model model) {
        var categories = categoryRepository.findAllByStatus(DefaultStatus.ACTIVE);
        model.addAttribute("categories", categories);

        List<CategoryProductDTO> categoryProducts = new ArrayList<>();
        categories.forEach(c -> {
            List<ItemProductDTO> products = productRepository.
                    findAllByCategoryIdAndStatus(c.getCategoryId(), DefaultStatus.ACTIVE).
                    stream().map(p -> ItemProductDTO.builder()
                            .productId(p.getProductId())
                            .name(p.getName())
                            .amount(p.getAmount())
                            .categoryId(p.getCategoryId())
                            .build()
                    ).collect(Collectors.toList());

            var categoryProduct = CategoryProductDTO.builder()
                    .categoryId(c.getCategoryId())
                    .name(c.getName())
                    .slug(c.getSlug())
                    .products(products)
                    .build();

            categoryProducts.add(categoryProduct);
        });

        model.addAttribute("categoryProducts", categoryProducts);

        return "frontend/index";
    }
}
