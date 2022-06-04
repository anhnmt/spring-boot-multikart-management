package com.example.multikart.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

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

    private Integer amount;
    private Float importPrice;
    private Float exportPrice;

    private String description;
    private Integer status;
}
