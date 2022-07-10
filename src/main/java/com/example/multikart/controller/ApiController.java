package com.example.multikart.controller;

import com.example.multikart.common.Const;
import com.example.multikart.common.Const.DefaultStatus;
import com.example.multikart.common.DataUtils;
import com.example.multikart.common.Utils;
import com.example.multikart.domain.dto.*;
import com.example.multikart.repo.ProductRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/")
@Slf4j
public class ApiController {
    @Autowired
    ResourceLoader resourceLoader;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/dvhc/provinces")
    @Cacheable("provinces")
    public List<ProvinceDTO> getProvinceList() throws IOException {
        List<ProvinceDTO> provinces = new ArrayList<>();

        var resource = resourceLoader.getResource("classpath:dvhc/province.json");
        if (!resource.exists()) {
            return provinces;
        }

        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));
        Map<Long, ProvinceDTO> maps = new Gson().fromJson(br, Map.class);
//        log.info("{}", maps);

        provinces.addAll(maps.values());
        return provinces;
    }

    @GetMapping("/dvhc/provinces/{code}")
    @Cacheable(cacheNames = "districts", key = "#code")
    public List<DistrictDTO> getDistrictList(@PathVariable("code") String code) throws IOException {
        List<DistrictDTO> districts = new ArrayList<>();

        String url = String.format("classpath:dvhc/districts/%s.json", code);
        var resource = resourceLoader.getResource(url);
        if (!resource.exists()) {
            return districts;
        }

        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));
        Map<Long, DistrictDTO> maps = new Gson().fromJson(br, Map.class);
//        log.info("{}", maps);

        districts.addAll(maps.values());
        return districts;
    }

    @GetMapping("/dvhc/districts/{code}")
    @Cacheable(cacheNames = "wards", key = "#code")
    public List<WardDTO> getWardList(@PathVariable("code") String code) throws IOException {
        List<WardDTO> wards = new ArrayList<>();

        String url = String.format("classpath:dvhc/wards/%s.json", code);
        var resource = resourceLoader.getResource(url);
        if (!resource.exists()) {
            return wards;
        }

        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));
        Map<Long, WardDTO> maps = new Gson().fromJson(br, Map.class);
//        log.info("{}", maps);

        wards.addAll(maps.values());
        return wards;
    }

    @PostMapping("/cart/update")
    public ResponseEntity<CartResponseDTO> updateCartQuantity(@Valid @ModelAttribute("cart") AddToCartRequestDTO input, HttpSession session) {
        var product = productRepository.findByProductIdAndStatus(input.getProductId(), DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(product)) {
            var result = CartResponseDTO.builder()
                    .status(false)
                    .message("Sản phẩm không tồn tại")
                    .build();
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        var carts = Utils.getCartSession(session);
        // add or update cart
        if (!Utils.checkExistCart(carts, input.getProductId())) {
            carts.add(new CartDTO(product, input.getQuantity()));
        } else {
            carts.forEach(c -> {
                if (c.getProductId().equals(input.getProductId())) {
                    c.setQuantity(input.getQuantity());
                }
            });
        }

        session.setAttribute("carts", carts);
        var total = Utils.getTotalPriceCart(carts);

        var itemTotal = Utils.getItemTotalPriceCart(carts, input.getProductId());

        var result = CartResponseDTO.builder()
                .status(true)
                .message("Cập nhật thành công")
                .cartCount(carts.size())
                .cartTotal(total)
                .itemTotal(itemTotal)
                .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
