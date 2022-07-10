package com.example.multikart.domain.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartResponseDTO {
    private Integer cartCount;
    private Float cartTotal;
    private Float itemTotal;
    private boolean status;
    private String message;
}
