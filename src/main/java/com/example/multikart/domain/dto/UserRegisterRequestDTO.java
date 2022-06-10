package com.example.multikart.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRegisterRequestDTO {
    private String name;
    private String email;
    private String password;
    private String confirmPassword;
}
