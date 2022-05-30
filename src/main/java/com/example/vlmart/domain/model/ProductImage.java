package com.example.vlmart.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ProductImage extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productImageId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    // Đường dẫn tới ảnh
    @Column(nullable = false)
    private String url;

    // Số thứ tự
    @Column(columnDefinition = "integer default 0")
    private Integer index;

    // Mô tả, ghi chú
    private String description;

    // Trạng thái
    @Column(columnDefinition = "integer default 1")
    private Integer status;
}
