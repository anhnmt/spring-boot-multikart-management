package com.example.multikart.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
public class RoleRequestDTO {
    @NotBlank
    private String name;

    private Integer status;
}
