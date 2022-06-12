package com.example.multikart.domain.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDTO {
    @NotBlank
    private String name;

    private String phone;

    @Email
    private String email;
    private String password;
    private Integer status;
}
