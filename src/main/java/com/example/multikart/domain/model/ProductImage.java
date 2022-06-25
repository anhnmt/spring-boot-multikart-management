package com.example.multikart.domain.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_images")
public class ProductImage extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productImageId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    // Đường dẫn tới ảnh
    @Column(nullable = false)
    private String url;

    // Số thứ tự
    @Column(name = "position", columnDefinition = "integer default 0")
    private Integer position;

    // Mô tả, ghi chú
    @Column(columnDefinition = "text")
    private String description;

    // Trạng thái
    @Column(name = "status", columnDefinition = "integer default 1", nullable = false)
    private Integer status;
}
