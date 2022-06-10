package com.example.multikart.domain.dto;

import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryProductDTO {
    private Long categoryId;
    private String name;
    private String slug;

    // Trạng thái
    private Integer status;

    private List<ItemProductDTO> products;


}
