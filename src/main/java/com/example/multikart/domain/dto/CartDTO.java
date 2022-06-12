package com.example.multikart.domain.dto;

import com.example.multikart.domain.model.Product;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO implements Serializable {
    private static final long serialVersionUID = -1813145955899712226L;

    private Long productId;
    private String name;
    private String slug;
    private String image;

    private Integer quantity;
    private Float price;

    public CartDTO(Product product) {
        productId = product.getProductId();
        name = product.getName();
        slug = product.getSlug();
        quantity = 1;
        price = product.getExportPrice();
    }
}