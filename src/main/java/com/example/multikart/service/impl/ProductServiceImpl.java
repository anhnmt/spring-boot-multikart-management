package com.example.multikart.service.impl;

import com.example.multikart.common.Const;
import com.example.multikart.common.DataUtils;
import com.example.multikart.domain.dto.ProductRequestDTO;
import com.example.multikart.domain.model.Product;
import com.example.multikart.repo.ProductRepository;
import com.example.multikart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public String findAllProducts(Model model) {
        var products = productRepository.findAllByStatus(Const.DefaultStatus.ACTIVE);
        model.addAttribute("products", products);

        return "backend/product/index";
    }

    @Override
    public String createProduct(Model model) {
        model.addAttribute("product", Product.builder().status(Const.DefaultStatus.ACTIVE).build());

        return "backend/product/create";
    }

    @Override
    public String storeProduct(ProductRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            model.addAttribute("product", input);

            return "backend/product/create";
        }

        var count = productRepository.countByNameAndStatus(input.getName(), Const.DefaultStatus.ACTIVE);
        if (count > 0) {
            result.rejectValue("name", "", "Tên đơn vị đã được sử dụng");
        }

        if (result.hasErrors()) {
            model.addAttribute("product", input);

            return "backend/product/create";
        }

        var newProduct = new Product(input);
        productRepository.save(newProduct);

        redirect.addFlashAttribute("success", "Thêm thành công");
        return "redirect:/dashboard/products";
    }

    @Override
    public String editProduct(Long id, Model model, RedirectAttributes redirect) {
        var product = productRepository.findByProductIdAndStatus(id, Const.DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(product)) {
            redirect.addFlashAttribute("error", "Đơn vị không tồn tại");

            return "redirect:/dashboard/products";
        }

        model.addAttribute("product", product);

        return "backend/product/edit";
    }

    @Override
    public String updateProduct(Long id, ProductRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        var product = productRepository.findByProductIdAndStatus(id, Const.DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(product)) {
            redirect.addFlashAttribute("error", "Đơn vị không tồn tại");

            return "redirect:/dashboard/products";
        }

        if (result.hasErrors()) {
            model.addAttribute("product", input);

            return "backend/product/create";
        }

        if (!product.getName().equals(input.getName())) {
            var count = productRepository.countByNameAndStatus(input.getName(), Const.DefaultStatus.ACTIVE);
            if (count > 0) {
                result.rejectValue("name", "", "Tên đơn vị đã được sử dụng");
            }

            product.setName(input.getName());
        }

        if (result.hasErrors()) {
            model.addAttribute("product", input);

            return "backend/product/create";
        }

        product.setStatus(input.getStatus());
        productRepository.save(product);

        redirect.addFlashAttribute("success", "Sửa thành công");
        return "redirect:/dashboard/products";
    }

    @Override
    public String deleteProduct(Long id, Model model, RedirectAttributes redirect) {
        var product = productRepository.findByProductIdAndStatus(id, Const.DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(product)) {
            redirect.addFlashAttribute("error", "Đơn vị không tồn tại");

            return "redirect:/dashboard/products";
        }

        product.setStatus(Const.DefaultStatus.DELETED);
        productRepository.save(product);

        redirect.addFlashAttribute("success", "Xóa thành công");
        return "redirect:/dashboard/products";
    }
}
