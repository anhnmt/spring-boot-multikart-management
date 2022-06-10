package com.example.multikart.domain.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemProductDTO {
    private Long productId;
    private Long categoryId;
    private Long unitId;
    private Long supplierId;

    private String name;

    private String slug;

    private Integer amount;

    private Float importPrice;

    private Float exportPrice;

    // Mô tả, ghi chú
    private String description;

    // Trạng thái
    private Integer status;
}
