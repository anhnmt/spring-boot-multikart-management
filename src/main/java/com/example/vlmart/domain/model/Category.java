package com.example.vlmart.domain.model;

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
    @Column(name = "status", columnDefinition = "integer default 1")
    private Integer status;
}
