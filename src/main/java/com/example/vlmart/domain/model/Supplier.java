package com.example.vlmart.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
public class Supplier extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long supplierId;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String website;

    @NotBlank
    private String address;

    private String description;
    private String taxCode;

    // Trạng thái
    @Column(columnDefinition = "integer default 1")
    private Integer status;
}
