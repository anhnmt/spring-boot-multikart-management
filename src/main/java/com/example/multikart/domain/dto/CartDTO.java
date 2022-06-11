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
public class CartDTO {
    private Long productId;
    private String name;
    private String slug;
    private String image;

    private Integer quantity;
    private Float price;
}
