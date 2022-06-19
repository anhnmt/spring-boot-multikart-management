package com.example.multikart.common;

import com.example.multikart.domain.dto.CartDTO;
import com.example.multikart.domain.model.Customer;
import com.example.multikart.domain.model.User;

import javax.servlet.http.HttpSession;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class Utils {

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public static String toSlug(String input) {
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

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

    public static Customer getCustomerSession(HttpSession session) {
        var customer = (Customer) session.getAttribute("customer");
        if (DataUtils.isNullOrEmpty(customer)) {
            return new Customer();
        }

        return customer;
    }

    public static User getUserSession(HttpSession session) {
        var user = (User) session.getAttribute("user");
        if (DataUtils.isNullOrEmpty(user)) {
            return new User();
        }

        return user;
    }
}
