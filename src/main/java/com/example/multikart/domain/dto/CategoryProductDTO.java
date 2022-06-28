package com.example.multikart.domain.dto;

import lombok.*;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryProductDTO implements Serializable {
    private Long categoryId;
    private String name;
    private String slug;

    // Trạng thái
    private Integer status;

    private Page<ItemProductDTO> products;


}
