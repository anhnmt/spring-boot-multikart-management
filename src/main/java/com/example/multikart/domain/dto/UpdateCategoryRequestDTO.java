package com.example.multikart.domain.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCategoryRequestDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String slug;

    private Integer status;
}
