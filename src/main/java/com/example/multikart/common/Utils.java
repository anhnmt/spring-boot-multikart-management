package com.example.multikart.common;

import com.example.multikart.domain.dto.CartDTO;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static boolean checkExistCart(List<CartDTO> carts, long id) {
        return carts.stream().anyMatch(c -> c.getProductId().equals(id));
    }

    public static List<CartDTO> getCartSession(HttpSession session) {
        var carts = (List<CartDTO>) session.getAttribute("carts");
        if (DataUtils.isNullOrEmpty(carts)) {
            return new ArrayList<>();
        }

        return carts;
    }

    public static float getTotalPriceCart(List<CartDTO> carts) {
        var total = 0.0f;
        return carts.stream().map(cart -> cart.getPrice() * cart.getQuantity()).reduce(total, Float::sum);
    }
}
