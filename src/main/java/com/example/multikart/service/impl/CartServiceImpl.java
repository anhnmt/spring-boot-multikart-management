package com.example.multikart.service.impl;

import com.example.multikart.common.Const.DefaultStatus;
import com.example.multikart.common.DataUtils;
import com.example.multikart.domain.dto.AddToCartRequestDTO;
import com.example.multikart.domain.dto.CartDTO;
import com.example.multikart.repo.ProductRepository;
import com.example.multikart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public String view(HttpSession session, Model model) {
        var carts = getCartSession(session);
        model.addAttribute("carts", carts);

        var total = 0.0f;
        total = carts.stream().map(cart -> cart.getPrice() * cart.getQuantity()).reduce(total, Float::sum);
        model.addAttribute("total", total);

        return "frontend/cart";
    }

    @Override
    public String addToCart(AddToCartRequestDTO input, HttpSession session, Model model, RedirectAttributes redirect) {
        var product = productRepository.findByProductIdAndStatus(input.getProductId(), DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(product)) {
            redirect.addFlashAttribute("error", "Sản phẩm không tồn tại");
            return "redirect:/";
        }

        var carts = getCartSession(session);
        // add or update cart
        if (!checkExistCart(carts, input.getProductId())) {
            carts.add(new CartDTO(product, input.getQuantity()));
        } else {
            carts.forEach(c -> {
                if (c.getProductId().equals(input.getProductId())) {
                    c.setQuantity(c.getQuantity() + input.getQuantity());
                }
            });
        }

        session.setAttribute("carts", carts);

        redirect.addFlashAttribute("success", "Thêm thành công");
        return "redirect:/cart";
    }

    @Override
    public String removeFromCart(Long id, HttpSession session, Model model, RedirectAttributes redirect) {
        var product = productRepository.findByProductIdAndStatus(id, DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(product)) {
            redirect.addFlashAttribute("error", "Sản phẩm không tồn tại");
            return "redirect:/cart";
        }

        var carts = getCartSession(session);
        // add or update cart
        if (!checkExistCart(carts, id)) {
            redirect.addFlashAttribute("error", "Sản phẩm không còn trong giỏ");
            return "redirect:/cart";
        } else {
            carts.removeIf(c -> c.getProductId().equals(id));
        }

        session.setAttribute("carts", carts);

        redirect.addFlashAttribute("success", "Xóa thành công");
        return "redirect:/cart";
    }

    private boolean checkExistCart(List<CartDTO> carts, long id) {
        return carts.stream().anyMatch(c -> c.getProductId().equals(id));
    }

    private List<CartDTO> getCartSession(HttpSession session) {
        var carts = (List<CartDTO>) session.getAttribute("carts");
        if (DataUtils.isNullOrEmpty(carts)) {
            return new ArrayList<>();
        }

        return carts;
    }
}
