package com.example.multikart.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class CreateUserRequestDTO {
    @NotNull
    private Long roleId;

    @NotBlank
    private String name;

    @Email
    private String email;
    @NotBlank
    private String password;
    private Integer status;
}
