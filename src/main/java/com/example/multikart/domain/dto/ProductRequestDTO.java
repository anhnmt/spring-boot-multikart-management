package com.example.multikart.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class ProductRequestDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String slug;

    private Long categoryId;
    private Long unitId;
    private Long supplierId;

    @Positive(message = "Số lượng phải là số dương")
    private Integer amount;
    @Positive(message = "Giá nhập phải là số dương")
    private Float importPrice;
    @Positive(message = "Giá xuất phải là số dương")
    private Float exportPrice;

    private String description;
    @Size(max = 3, message = "Trạng thái không hợp lệ")
    private Integer status;
}
