package com.example.multikart.domain.dto;

import com.example.multikart.common.DataUtils;
import com.example.multikart.domain.model.OrderDetail;
import com.example.multikart.domain.model.Product;
import com.example.multikart.domain.model.ProductImage;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO implements Serializable {
    private static final long serialVersionUID = -1813145955899712226L;

    private Long orderDetailId;

    private Long productId;

    private String name;
    private String slug;
    private String image;
    private Integer quantity;
    private Float price;

    public OrderDetailDTO(OrderDetail orderDetail, Product product, ProductImage productImage) {
        orderDetailId = orderDetail.getOrderDetailId();
        productId = orderDetail.getProductId();
        quantity = orderDetail.getAmount();
        price = orderDetail.getPrice();

        if (!DataUtils.isNullOrEmpty(product)) {
            name = product.getName();
            slug = product.getSlug();
        }

        if (!DataUtils.isNullOrEmpty(productImage)) {
            image = productImage.getUrl();
        }
    }
}
