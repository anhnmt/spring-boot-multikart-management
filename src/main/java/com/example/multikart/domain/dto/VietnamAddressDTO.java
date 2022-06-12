package com.example.multikart.domain.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VietnamAddressDTO {
    private List<ProvinceDTO> data;
}
