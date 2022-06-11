package com.example.multikart.service.impl;

import com.example.multikart.common.Const.DefaultStatus;
import com.example.multikart.common.DataUtils;
import com.example.multikart.domain.dto.ItemProductDTO;
import com.example.multikart.repo.ProductRepository;
import com.example.multikart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public String view(HttpSession session, Model model) {
        var carts = (List<ItemProductDTO>) session.getAttribute("carts");
        model.addAttribute("carts", carts);

        return "frontend/cart";
    }

    @Override
    public String addToCart(Long id, HttpSession session, Model model, RedirectAttributes redirect) {
        var product = productRepository.findByProductIdAndStatus(id, DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(product)) {
            redirect.addFlashAttribute("error", "Sản phẩm không tồn tại");
            return "redirect:/";
        }

        redirect.addFlashAttribute("success", "Thêm thành công");
        return "redirect:/cart";
    }
}
