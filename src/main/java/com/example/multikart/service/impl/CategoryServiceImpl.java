package com.example.multikart.service.impl;

import com.example.multikart.common.Const.DefaultStatus;
import com.example.multikart.common.DataUtils;
import com.example.multikart.domain.dto.CreateCategoryRequestDTO;
import com.example.multikart.domain.dto.UpdateCategoryRequestDTO;
import com.example.multikart.domain.model.Category;
import com.example.multikart.repo.CategoryRepository;
import com.example.multikart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public String findAllCategories(Model model) {
        var categories = categoryRepository.findAllByStatus(DefaultStatus.ACTIVE);
        model.addAttribute("categories", categories);

        return "backend/category/index";
    }

    @Override
    public String createCategory(Model model) {
        model.addAttribute("category", Category.builder().status(DefaultStatus.ACTIVE).build());

        return "backend/category/create";
    }

    @Override
    public String storeCategory(CreateCategoryRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            model.addAttribute("category", input);

            return "backend/category/create";
        }

        var count = categoryRepository.countBySlugAndStatus(input.getSlug(), DefaultStatus.ACTIVE);
        if (count > 0) {
            result.rejectValue("slug", "", "Đường dẫn đã được sử dụng");
        }

        if (result.hasErrors()) {
            model.addAttribute("category", input);

            return "backend/category/create";
        }

        var newCategory = new Category(input);
        categoryRepository.save(newCategory);

        redirect.addFlashAttribute("success", "Thêm thành công");
        return "redirect:/dashboard/categories";
    }

    @Override
    public String editCategory(Long id, Model model, RedirectAttributes redirect) {
        var category = categoryRepository.findByCategoryIdAndStatus(id, DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(category)) {
            redirect.addFlashAttribute("error", "Danh mục không tồn tại");

            return "redirect:/dashboard/categories";
        }

        model.addAttribute("category", category);

        return "backend/category/edit";
    }

    @Override
    public String updateCategory(Long id, UpdateCategoryRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        var category = categoryRepository.findByCategoryIdAndStatus(id, DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(category)) {
            redirect.addFlashAttribute("error", "Danh mục không tồn tại");

            return "redirect:/dashboard/categories";
        }

        if (result.hasErrors()) {
            model.addAttribute("category", input);

            return "backend/category/create";
        }

        if (!category.getSlug().equals(input.getSlug())) {
            var count = categoryRepository.countBySlugAndStatus(input.getSlug(), DefaultStatus.ACTIVE);
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

        redirect.addFlashAttribute("success", "Sửa thành công");
        return "redirect:/dashboard/categories";
    }

    @Override
    public String deleteCategory(Long id, Model model, RedirectAttributes redirect) {
        var category = categoryRepository.findByCategoryIdAndStatus(id, DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(category)) {
            redirect.addFlashAttribute("error", "Danh mục không tồn tại");

            return "redirect:/dashboard/categories";
        }

        category.setStatus(DefaultStatus.DELETED);
        categoryRepository.save(category);

        redirect.addFlashAttribute("success", "Xóa thành công");
        return "redirect:/dashboard/categories";
    }
}
