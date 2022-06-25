package com.example.multikart.service.impl;

import com.example.multikart.common.Const.DefaultStatus;
import com.example.multikart.common.DataUtils;
import com.example.multikart.common.Utils;
import com.example.multikart.domain.dto.AddToCartRequestDTO;
import com.example.multikart.domain.dto.CartDTO;
import com.example.multikart.repo.ProductRepository;
import com.example.multikart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public String view(HttpSession session, Model model) {
        var carts = Utils.getCartSession(session);
        model.addAttribute("carts", carts);

        var total = Utils.getTotalPriceCart(carts);
        model.addAttribute("total", total);

        return "frontend/cart";
    }

    @Override
    public String addToCart(AddToCartRequestDTO input, HttpSession session, Model model, RedirectAttributes redirect) {
        var product = productRepository.findWithImageByProductIdAndStatus(input.getProductId(), DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(product)) {
            redirect.addFlashAttribute("error", "Sản phẩm không tồn tại");
            return "redirect:/";
        }

        var carts = Utils.getCartSession(session);
        // add or update cart
        if (!Utils.checkExistCart(carts, input.getProductId())) {
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

        var carts = Utils.getCartSession(session);
        // add or update cart
        if (!Utils.checkExistCart(carts, id)) {
            redirect.addFlashAttribute("error", "Sản phẩm không còn trong giỏ");
            return "redirect:/cart";
        } else {
            carts.removeIf(c -> c.getProductId().equals(id));
        }

        session.setAttribute("carts", carts);

        redirect.addFlashAttribute("success", "Xóa thành công");
        return "redirect:/cart";
    }
}
