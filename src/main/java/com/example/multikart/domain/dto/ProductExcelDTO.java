package com.example.multikart.domain.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductExcelDTO implements Serializable {
    private static final long serialVersionUID = -1813145955899712226L;

    @NotBlank
    private String name;
    @NotBlank
    private String slug;

    private String categoryName;
    private String unitName;
    private String supplierName;

    private Integer amount;
    private Float importPrice;
    private Float exportPrice;

    private String description;
    private Integer status;
}
