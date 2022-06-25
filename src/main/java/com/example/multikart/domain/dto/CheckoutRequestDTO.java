package com.example.multikart.domain.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutRequestDTO {
    @NotBlank
    private String name;

    private Long customerId;

    private String phone;

    @Email
    private String email;

    private String provinceId;

    private String districtId;

    private String wardId;

    private String address;

    private String description;

    private Long paymentId;

    private Long transportId;
}
