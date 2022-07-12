package com.example.multikart.service.impl;

import com.example.multikart.common.Const.DefaultStatus;
import com.example.multikart.common.DataUtils;
import com.example.multikart.domain.dto.CategoryRequestDTO;
import com.example.multikart.domain.dto.ScreenRedis;
import com.example.multikart.domain.model.Category;
import com.example.multikart.repo.CategoryRepository;
import com.example.multikart.repo.ProductRepository;
import com.example.multikart.service.CategoryService;
import com.example.multikart.service.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RedisCache redisCache;

    @Override
    public String findAllCategories(Model model) {
        var categories = categoryRepository.findAllByStatusNot(DefaultStatus.DELETED);
        model.addAttribute("categories", categories);

        return "backend/category/index";
    }

    @Override
    public String createCategory(Model model) {
        model.addAttribute("category", Category.builder().status(DefaultStatus.ACTIVE).build());

        return "backend/category/create";
    }

    @Override
    public String storeCategory(CategoryRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            model.addAttribute("category", input);

            return "backend/category/create";
        }

        var count = categoryRepository.countBySlugAndStatusNot(input.getSlug(), DefaultStatus.DELETED);
        if (count > 0) {
            result.rejectValue("slug", "", "Đường dẫn đã được sử dụng");
        }

        if (result.hasErrors()) {
            model.addAttribute("category", input);

            return "backend/category/create";
        }

        var newCategory = new Category(input);
        categoryRepository.save(newCategory);

        redisCache.delete(ScreenRedis.HOME.name());
        redirect.addFlashAttribute("success", "Thêm thành công");
        return "redirect:/dashboard/categories";
    }

    @Override
    public String editCategory(Long id, Model model, RedirectAttributes redirect) {
        var category = categoryRepository.findByCategoryIdAndStatusNot(id, DefaultStatus.DELETED);
        if (DataUtils.isNullOrEmpty(category)) {
            redirect.addFlashAttribute("error", "Danh mục không tồn tại");

            return "redirect:/dashboard/categories";
        }

        model.addAttribute("category", category);

        return "backend/category/edit";
    }

    @Override
    public String updateCategory(Long id, CategoryRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        var category = categoryRepository.findByCategoryIdAndStatusNot(id, DefaultStatus.DELETED);
        if (DataUtils.isNullOrEmpty(category)) {
            redirect.addFlashAttribute("error", "Danh mục không tồn tại");

            return "redirect:/dashboard/categories";
        }

        if (result.hasErrors()) {
            model.addAttribute("category", input);

            return "backend/category/create";
        }

        if (!category.getSlug().equals(input.getSlug())) {
            var count = categoryRepository.countBySlugAndStatusNot(input.getSlug(), DefaultStatus.DELETED);
            if (count > 0) {
                result.rejectValue("slug", "", "Đường dẫn đã được sử dụng");
            }

            category.setSlug(input.getSlug());
        }

        if (result.hasErrors()) {
            model.addAttribute("category", input);

            return "backend/category/create";
        }

        category.setName(input.getName());
        category.setStatus(input.getStatus());
        categoryRepository.save(category);

        redisCache.delete(ScreenRedis.HOME.name());
        redirect.addFlashAttribute("success", "Sửa thành công");
        return "redirect:/dashboard/categories";
    }

    @Override
    public String deleteCategory(Long id, Model model, RedirectAttributes redirect) {
        var category = categoryRepository.findByCategoryIdAndStatusNot(id, DefaultStatus.DELETED);
        if (DataUtils.isNullOrEmpty(category)) {
            redirect.addFlashAttribute("error", "Danh mục không tồn tại");

            return "redirect:/dashboard/categories";
        }

        category.setStatus(DefaultStatus.DELETED);
        categoryRepository.save(category);

        redisCache.delete(ScreenRedis.HOME.name());
        redirect.addFlashAttribute("success", "Xóa thành công");
        return "redirect:/dashboard/categories";
    }

    @Override
    public String frontendCategory(String slug, Optional<Integer> page, Optional<Integer> size, Model model, RedirectAttributes redirect) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(12);
        model.addAttribute("slug", slug);
        model.addAttribute("currentPage", currentPage);

        var category = categoryRepository.findBySlugAndStatus(slug, DefaultStatus.ACTIVE);
        model.addAttribute("category", category);

        var pageRequest = PageRequest.of(currentPage - 1, pageSize);
        var products = productRepository.findPageAllByCategoryIdAndStatus(category.getCategoryId(), DefaultStatus.ACTIVE, pageRequest);
        model.addAttribute("products", products);

        int totalPages = products.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "frontend/category";
    }
}
