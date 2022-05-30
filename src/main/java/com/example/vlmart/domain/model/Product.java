package com.example.vlmart.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
public class Product extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long productId;

    @NotBlank
    private String name;

    @NotBlank
    private String slug;

    @Column(columnDefinition = "integer default 0")
    private Integer amount;

    @Column(name = "import_price", columnDefinition = "float default 0")
    private Float importPrice;

    @Column(name = "export_price", columnDefinition = "float default 0")
    private Float exportPrice;

    // Mô tả, ghi chú
    private String description;

    // Trạng thái
    @Column(columnDefinition = "integer default 1")
    private Integer status;
}
