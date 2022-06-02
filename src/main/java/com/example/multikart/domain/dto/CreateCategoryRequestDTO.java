package com.example.multikart.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
public class CreateCategoryRequestDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String slug;

    private Integer status;
}
