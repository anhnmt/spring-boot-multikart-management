package com.example.multikart.service.impl;

import com.example.multikart.common.Const.DefaultStatus;
import com.example.multikart.common.DataUtils;
import com.example.multikart.common.Utils;
import com.example.multikart.domain.model.ProductImage;
import com.example.multikart.repo.ProductImageRepository;
import com.example.multikart.repo.ProductRepository;
import com.example.multikart.service.ProductImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    private ProductImageRepository productImageRepository;
    @Autowired
    private ProductRepository productRepository;

    private String productDirectory = "uploads/images/products";

    @Override
    public String findAllProductImages(Long id, Model model, RedirectAttributes redirect) {
        var product = productRepository.findByProductIdAndStatus(id, DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(product)) {
            redirect.addFlashAttribute("error", "Sản phẩm không tồn tại");

            return "redirect:/dashboard/products";
        }
        model.addAttribute("product", product);

        var images = productImageRepository.findAllByProductIdAndStatusOrderByPositionAsc(id, DefaultStatus.ACTIVE).stream().filter(Objects::nonNull).peek(x -> x.setUrl(DataUtils.getValueOrDefault(x.getUrl(), "assets/images/no_image.jpg"))).collect(Collectors.toList());

        model.addAttribute("images", images);

        var min = productImageRepository.findMinPositionByProductIdAndStatus(id, DefaultStatus.ACTIVE);
        model.addAttribute("min", min);

        var max = productImageRepository.findMaxPositionByProductIdAndStatus(id, DefaultStatus.ACTIVE);
        model.addAttribute("max", max);

        return "backend/product_image/index";
    }

    @Override
    public String upload(Long id, MultipartFile file, RedirectAttributes redirect) throws IOException {
        var product = productRepository.findByProductIdAndStatus(id, DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(product)) {
            redirect.addFlashAttribute("error", "Sản phẩm không tồn tại");

            return "redirect:/dashboard/products";
        }

        if (DataUtils.isNullOrEmpty(file)) {
            redirect.addFlashAttribute("error", "Vui lòng chọn file");

            return "redirect:/dashboard/products/" + id + "/images";
        }

        Path uploadPath = Paths.get(productDirectory);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = Utils.toSlug(StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
        String uploadDir = productDirectory + "/" + id;

        try {
            Utils.saveFile(uploadDir, fileName, file);

            var count = productImageRepository.countByProductIdAndStatus(id, DefaultStatus.ACTIVE);
            var productImage = ProductImage.builder()
                    .productId(id)
                    .url(uploadDir + "/" + fileName)
                    .position(count)
                    .status(DefaultStatus.ACTIVE)
                    .build();
            productImageRepository.save(productImage);

            redirect.addFlashAttribute("success", "Thêm thành công");
        } catch (IOException e) {
            e.printStackTrace();
            redirect.addFlashAttribute("error", "Thêm không thành công");
        }

        return "redirect:/dashboard/products/" + id + "/images";
    }

    @Override
    @Transactional
    public String delete(Long productId, Long productImageId, RedirectAttributes redirect) {
        var product = productRepository.findByProductIdAndStatus(productId, DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(product)) {
            redirect.addFlashAttribute("error", "Sản phẩm không tồn tại");

            return "redirect:/dashboard/products";
        }

        var productImage = productImageRepository.findByProductImageIdAndStatus(productImageId, DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(productImage)) {
            redirect.addFlashAttribute("error", "Hình ảnh không tồn tại");

            return "redirect:/dashboard/products/" + productId + "/images";
        }

        productImage.setStatus(DefaultStatus.DELETED);
        productImageRepository.save(productImage);
        productImageRepository.updatePositionDelete(productId, productImageId, productImage.getPosition(), DefaultStatus.ACTIVE);

        redirect.addFlashAttribute("success", "Xóa thành công");

        return "redirect:/dashboard/products/" + productId + "/images";
    }

    @Override
    @Transactional
    public String up(Long productId, Long productImageId, RedirectAttributes redirect) {
        var product = productRepository.findByProductIdAndStatus(productId, DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(product)) {
            redirect.addFlashAttribute("error", "Sản phẩm không tồn tại");

            return "redirect:/dashboard/products";
        }

        var productImage = productImageRepository.findByProductImageIdAndStatus(productImageId, DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(productImage)) {
            redirect.addFlashAttribute("error", "Hình ảnh không tồn tại");

            return "redirect:/dashboard/products/" + productId + "/images";
        }

        productImageRepository.updatePositionUp(productId, productImageId, productImage.getPosition(), DefaultStatus.ACTIVE);
        productImage.setPosition(productImage.getPosition() - 1);
        productImageRepository.save(productImage);

        redirect.addFlashAttribute("success", "Cập nhật thành công");

        return "redirect:/dashboard/products/" + productId + "/images";
    }

    @Override
    @Transactional
    public String down(Long productId, Long productImageId, RedirectAttributes redirect) {
        var product = productRepository.findByProductIdAndStatus(productId, DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(product)) {
            redirect.addFlashAttribute("error", "Sản phẩm không tồn tại");

            return "redirect:/dashboard/products";
        }

        var productImage = productImageRepository.findByProductImageIdAndStatus(productImageId, DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(productImage)) {
            redirect.addFlashAttribute("error", "Hình ảnh không tồn tại");

            return "redirect:/dashboard/products/" + productId + "/images";
        }

        productImageRepository.updatePositionDown(productId, productImageId, productImage.getPosition(), DefaultStatus.ACTIVE);
        productImage.setPosition(productImage.getPosition() + 1);
        productImageRepository.save(productImage);

        redirect.addFlashAttribute("success", "Cập nhật thành công");

        return "redirect:/dashboard/products/" + productId + "/images";
    }
}
