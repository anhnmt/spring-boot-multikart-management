package com.example.multikart.domain.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    private Long userId;
    private Long roleId;

    @NotBlank
    private String name;

    @Email
    private String email;
    private String password;
    private Integer status;
}
