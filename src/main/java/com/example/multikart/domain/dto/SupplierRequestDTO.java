package com.example.multikart.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
public class SupplierRequestDTO {
    private Long supplierId;
    @NotBlank
    private String name;

    @Email
    private String email;

    private String address;
    private String phone;
    private String taxCode;
    private String website;

    private Integer status;
}
