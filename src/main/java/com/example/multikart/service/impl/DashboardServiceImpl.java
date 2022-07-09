package com.example.multikart.service.impl;

import com.example.multikart.common.Const;
import com.example.multikart.repo.*;
import com.example.multikart.service.DashboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Arrays;

@Service
@Slf4j
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository oderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public String dashboard(Model model) {
        var countProduct = productRepository.countByStatusIn(Arrays.asList(Const.DefaultStatus.ACTIVE, Const.DefaultStatus.DISABLED));
        model.addAttribute("countProduct", countProduct);
        var countOder = oderRepository.count();
        model.addAttribute("countOder", countOder);
        var totalRevenue = oderRepository.sumTotalRevenue(Const.OrderStatus.SHIPPED);
        model.addAttribute("totalRevenue", totalRevenue);
        var totalUser = userRepository.count();
        model.addAttribute("totalUser", totalUser);

        var sort = Sort.by("updatedAt").descending();
        var pageRequest = PageRequest.of(0, 6, sort);
        var orders = orderRepository.findAllByStatusNotAndPaginate(Const.OrderStatus.DELETED, Const.DefaultStatus.ACTIVE, pageRequest);
        model.addAttribute("lastOrders", orders);

        return "backend/index";
    }


}
