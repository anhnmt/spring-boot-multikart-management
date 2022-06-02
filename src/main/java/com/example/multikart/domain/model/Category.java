package com.example.multikart.domain.model;

import com.example.multikart.domain.dto.CreateCategoryRequestDTO;
import com.example.multikart.domain.dto.UpdateCategoryRequestDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Category extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @NotBlank
    private String name;

    @NotBlank
    private String slug;

    // Trạng thái
    @Column(name = "status", columnDefinition = "integer default 1", nullable = false)
    private Integer status;

    public Category(CreateCategoryRequestDTO input) {
        name = input.getName();
        slug = input.getSlug();
        status = input.getStatus();
    }

    public Category(UpdateCategoryRequestDTO input) {
        name = input.getName();
        slug = input.getSlug();
        status = input.getStatus();
    }
}
