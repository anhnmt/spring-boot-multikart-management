package com.example.multikart.service.impl;

import com.example.multikart.common.Const.DefaultStatus;
import com.example.multikart.domain.dto.CategoryProductDTO;
import com.example.multikart.domain.dto.ScreenRedis;
import com.example.multikart.repo.CategoryRepository;
import com.example.multikart.repo.ProductRepository;
import com.example.multikart.service.HomeService;
import com.example.multikart.service.RedisCache;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RedisCache redisCache;

    @Override
    public String home(Model model) {
        var categories = categoryRepository.findAllByStatus(DefaultStatus.ACTIVE);
        model.addAttribute("categories", categories);
        List<CategoryProductDTO> categoryProducts = new ArrayList<>();
        List<CategoryProductDTO> categoryProductsCache = redisCache.getObject(ScreenRedis.HOME.name(), categories, new TypeReference<>() {
        });
        if (categoryProductsCache == null) {
            categories.parallelStream().forEach(c -> {
                var pageRequest = PageRequest.of(0, 6);
                var products = productRepository.findAllByCategoryIdAndStatus(c.getCategoryId(), DefaultStatus.ACTIVE, pageRequest);
                var categoryProduct = CategoryProductDTO.builder().categoryId(c.getCategoryId()).name(c.getName()).slug(c.getSlug()).products(products).build();

                categoryProducts.add(categoryProduct);
            });
            redisCache.putObject(ScreenRedis.HOME.name(), categories, categoryProducts);
            model.addAttribute("categoryProducts", categoryProducts);
        } else {
            model.addAttribute("categoryProducts", categoryProductsCache);
        }


        return "frontend/index";
    }

    @Override
    public String search(Optional<String> search, Optional<Integer> page, Optional<Integer> size, Model model) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(12);
        String searchString = search.orElse("");

        model.addAttribute("search", searchString);
        model.addAttribute("currentPage", currentPage);

        var pageRequest = PageRequest.of(currentPage - 1, pageSize);
        var products = productRepository.findPageAllByNameAndStatus(searchString, DefaultStatus.ACTIVE, pageRequest);
        model.addAttribute("products", products);

        int totalPages = products.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "frontend/search";
    }
}
