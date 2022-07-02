package com.example.multikart.service.impl;

import com.example.multikart.common.Const.DefaultStatus;
import com.example.multikart.common.Const.OrderStatus;
import com.example.multikart.common.Utils;
import com.example.multikart.domain.dto.CheckoutRequestDTO;
import com.example.multikart.domain.dto.ScreenRedis;
import com.example.multikart.domain.model.Customer;
import com.example.multikart.domain.model.Order;
import com.example.multikart.domain.model.OrderDetail;
import com.example.multikart.repo.*;
import com.example.multikart.service.CheckoutService;
import com.example.multikart.service.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    @Autowired
    private TransportRepository transportRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RedisCache redisCache;

    @Override
    public String view(HttpSession session, Model model) {
        var customer = (Customer) session.getAttribute("customer");

        var checkout = CheckoutRequestDTO.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .districtId(customer.getDistrictId())
                .provinceId(customer.getProvinceId())
                .wardId(customer.getWardId())
                .transportId(0L)
                .paymentId(0L)
                .build();

        model.addAttribute("checkout", checkout);

        var carts = Utils.getCartSession(session);
        model.addAttribute("carts", carts);

        var total = Utils.getTotalPriceCart(carts);
        model.addAttribute("total", total);

        var transports = transportRepository.findAllByStatus(DefaultStatus.ACTIVE);
        model.addAttribute("transports", transports);

        var payments = paymentRepository.findAllByStatus(DefaultStatus.ACTIVE);
        model.addAttribute("payments", payments);

        return "frontend/checkout";
    }

    @Override
    @Transactional
    public String checkout(CheckoutRequestDTO input, BindingResult result, HttpSession session, Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            model.addAttribute("checkout", input);

            return "redirect:/checkout";
        }

        try {
            var customer = (Customer) session.getAttribute("customer");

            var carts = Utils.getCartSession(session);
            if (carts.isEmpty()) {
                redirect.addFlashAttribute("error", "Không có sản phẩm nào trong giỏ hàng");
                return "redirect:/cart";
            }

            var total = Utils.getTotalPriceCart(carts);

            var newOrder = orderRepository.save(Order.builder()
                    .customerId(customer.getCustomerId())
                    .name(customer.getName())
                    .paymentId(input.getPaymentId())
                    .transportId(input.getTransportId())
                    .totalPrice(total)
                    .status(OrderStatus.PENDING)
                    .build());

            carts.parallelStream().forEach(cart -> {
                orderDetailRepository.save(OrderDetail.builder()
                        .orderId(newOrder.getOrderId())
                        .productId(cart.getProductId())
                        .amount(cart.getQuantity())
                        .price(cart.getPrice())
                        .build());

                productRepository.updateMinusByProductIdAndAmountAndStatus(cart.getProductId(), cart.getQuantity(), DefaultStatus.ACTIVE);
            });

            redisCache.delete(ScreenRedis.PRODUCT.name());
            redisCache.delete(ScreenRedis.HOME.name());
        } catch (Exception e) {
            e.printStackTrace();
            redirect.addFlashAttribute("error", "Đặt hàng thất bại");
        }

        session.removeAttribute("carts");
        redirect.addFlashAttribute("success", "Đặt hàng thành công");
        return "redirect:/";
    }
}
