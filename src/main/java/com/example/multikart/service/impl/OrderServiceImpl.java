package com.example.multikart.service.impl;

import com.example.multikart.common.Const;
import com.example.multikart.common.Const.DefaultStatus;
import com.example.multikart.common.Const.OrderStatus;
import com.example.multikart.common.DataUtils;
import com.example.multikart.common.Utils;
import com.example.multikart.domain.model.Customer;
import com.example.multikart.repo.OrderDetailRepository;
import com.example.multikart.repo.OrderRepository;
import com.example.multikart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public String findAllOrders(Model model) {
        var orders = orderRepository.findAllByStatus(DefaultStatus.ACTIVE);
        model.addAttribute("orders", orders);

        return "backend/order/index";
    }

    @Override
    public String viewOrder(Long id, Model model, RedirectAttributes redirect) {
        var order = orderRepository.findByOrderIdAndStatusNot(id, OrderStatus.DELETED);
        if (DataUtils.isNullOrEmpty(order)) {
            redirect.addFlashAttribute("error", "Hóa đơn không tồn tại");

            return "redirect:/dashboard/orders";
        }

        model.addAttribute("order", order);

        var orderDetails = orderDetailRepository.findAllByOrderIdStatusNot(id, OrderStatus.DELETED);
        model.addAttribute("orderDetails", orderDetails);

        return "backend/order/detail";
    }

    @Override
    public String frontendViewOrder(Long id, HttpSession session, Model model, RedirectAttributes redirect) {
        var customer = Utils.getCustomerSession(session);

        var order = orderRepository.findByOrderIdAndCustomerIdAndStatusNot(id, customer.getCustomerId(), OrderStatus.DELETED);
        if (DataUtils.isNullOrEmpty(order)) {
            redirect.addFlashAttribute("error", "Hóa đơn không tồn tại");

            return "redirect:/";
        }
        model.addAttribute("order", order);

        var orderDetails = orderDetailRepository.findAllByOrderIdStatusNot(id, OrderStatus.DELETED);
        model.addAttribute("orderDetails", orderDetails);

        return "frontend/order-success";
    }

    @Override
    public String frontendListOrder(HttpSession session, Model model, RedirectAttributes redirect) {
        var customer = Utils.getCustomerSession(session);

        var orders = orderRepository.findAllByCustomerIdAndStatusNot(customer.getCustomerId(), OrderStatus.DELETED);
        model.addAttribute("orders", orders);

        return "frontend/order";
    }
}
