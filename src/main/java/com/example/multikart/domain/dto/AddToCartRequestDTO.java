package com.example.multikart.domain.dto;

import com.example.multikart.domain.model.Product;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddToCartRequestDTO implements Serializable {
    private static final long serialVersionUID = -1813145955899712226L;

    private Long productId;

    private Integer quantity;
}
