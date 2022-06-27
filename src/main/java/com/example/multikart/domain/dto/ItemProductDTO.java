package com.example.multikart.domain.dto;

import com.example.multikart.common.DataUtils;
import com.example.multikart.domain.model.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemProductDTO {
    private Long productId;

    private Long categoryId;
    private String categoryName;

    private Long unitId;
    private String unitName;

    private Long supplierId;
    private String supplierName;

    private String name;

    private String slug;

    private Integer amount;

    private String image;

    private Float importPrice;

    private Float exportPrice;

    // Mô tả, ghi chú
    private String description;

    // Trạng thái
    private Integer status;

    public ItemProductDTO(Product product, Category category, ProductImage productImage) {
        productId = product.getProductId();
        name = product.getName();
        slug = product.getSlug();
        amount = product.getAmount();
        importPrice = product.getImportPrice();
        exportPrice = product.getExportPrice();
        description = product.getDescription();
        status = product.getStatus();

        if (!DataUtils.isNullOrEmpty(category)) {
            categoryId = category.getCategoryId();
            categoryName = category.getName();
        }

        if (!DataUtils.isNullOrEmpty(productImage)) {
            image = DataUtils.getValueOrDefault(productImage.getUrl(), "assets/images/no_image.jpg");
        } else {
            image = "assets/images/no_image.jpg";
        }
    }

    public ItemProductDTO(Product product, Category category, Unit unit, ProductImage productImage) {
        productId = product.getProductId();
        name = product.getName();
        slug = product.getSlug();
        amount = product.getAmount();
        importPrice = product.getImportPrice();
        exportPrice = product.getExportPrice();
        description = product.getDescription();
        status = product.getStatus();

        if (!DataUtils.isNullOrEmpty(category)) {
            categoryId = category.getCategoryId();
            categoryName = category.getName();
        }

        if (!DataUtils.isNullOrEmpty(unit)) {
            unitId = unit.getUnitId();
            unitName = unit.getName();
        }

        if (!DataUtils.isNullOrEmpty(productImage)) {
            image = DataUtils.getValueOrDefault(productImage.getUrl(), "assets/images/no_image.jpg");
        } else {
            image = "assets/images/no_image.jpg";
        }
    }

    public ItemProductDTO(Product product, Unit unit, ProductImage productImage) {
        productId = product.getProductId();
        name = product.getName();
        slug = product.getSlug();
        amount = product.getAmount();
        importPrice = product.getImportPrice();
        exportPrice = product.getExportPrice();
        description = product.getDescription();
        status = product.getStatus();

        if (!DataUtils.isNullOrEmpty(unit)) {
            unitId = unit.getUnitId();
            unitName = unit.getName();
        }

        if (!DataUtils.isNullOrEmpty(productImage)) {
            image = DataUtils.getValueOrDefault(productImage.getUrl(), "assets/images/no_image.jpg");
        } else {
            image = "assets/images/no_image.jpg";
        }
    }

    public ItemProductDTO(Product product, Category category, Unit unit, Supplier supplier, ProductImage productImage) {
        productId = product.getProductId();
        name = product.getName();
        slug = product.getSlug();
        amount = product.getAmount();
        importPrice = product.getImportPrice();
        exportPrice = product.getExportPrice();
        description = product.getDescription();
        status = product.getStatus();

        if (!DataUtils.isNullOrEmpty(category)) {
            categoryId = category.getCategoryId();
            categoryName = category.getName();
        }

        if (!DataUtils.isNullOrEmpty(unit)) {
            unitId = unit.getUnitId();
            unitName = unit.getName();
        }

        if (!DataUtils.isNullOrEmpty(supplier)) {
            supplierId = supplier.getSupplierId();
            supplierName = supplier.getName();
        }

        if (!DataUtils.isNullOrEmpty(productImage)) {
            image = DataUtils.getValueOrDefault(productImage.getUrl(), "assets/images/no_image.jpg");
        } else {
            image = "assets/images/no_image.jpg";
        }
    }
}
