package com.example.multikart.common;

import com.example.multikart.domain.dto.CartDTO;
import com.example.multikart.domain.dto.UserDTO;
import com.example.multikart.domain.model.Customer;
import com.example.multikart.domain.model.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
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

    public static String getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1)).orElse("");
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

    public static float getItemTotalPriceCart(List<CartDTO> carts, Long id) {
        var total = 0.0f;
        return carts.stream().filter(x -> x.getProductId().equals(id)).map(cart -> cart.getPrice() * cart.getQuantity()).reduce(total, Float::sum);
    }

    public static Customer getCustomerSession(HttpSession session) {
        var customer = (Customer) session.getAttribute("customer");
        if (DataUtils.isNullOrEmpty(customer)) {
            return new Customer();
        }

        return customer;
    }

    public static UserDTO getUserSession(HttpSession session) {
        var user = (UserDTO) session.getAttribute("user");
        if (DataUtils.isNullOrEmpty(user)) {
            return new UserDTO();
        }

        return user;
    }

    public static void saveFile(String uploadDir, String filename, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(filename);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + filename, ioe);
        }
    }
}
