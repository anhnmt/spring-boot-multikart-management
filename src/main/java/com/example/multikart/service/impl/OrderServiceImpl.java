package com.example.multikart.service.impl;

import com.example.multikart.common.Const.DefaultStatus;
import com.example.multikart.common.DataUtils;
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
        var order = orderRepository.findByOrderIdAndStatus(id, DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(order)) {
            redirect.addFlashAttribute("error", "Hóa đơn không tồn tại");

            return "redirect:/dashboard/orders";
        }

        model.addAttribute("order", order);

        return "backend/order/edit";
    }

    @Override
    public String frontendViewOrder(Long id, HttpSession session, Model model, RedirectAttributes redirect) {
        var customer = (Customer) session.getAttribute("customer");

        var order = orderRepository.findByOrderIdAndCustomerIdAndStatus(id, customer.getCustomerId(), DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(order)) {
            redirect.addFlashAttribute("error", "Hóa đơn không tồn tại");

            return "redirect:/";
        }
        model.addAttribute("order", order);

        var orderDetails = orderDetailRepository.findAllByOrderIdStatus(id, DefaultStatus.ACTIVE);
        model.addAttribute("orderDetails", orderDetails);

        return "frontend/order-success";
    }
}
