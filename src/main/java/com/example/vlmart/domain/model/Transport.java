package com.example.vlmart.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
public class Transport extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transport_id")
    private Long transportId;

    @NotBlank
    private String name;

    // Mô tả, ghi chú
    private String description;

    // Trạng thái
    @Column(columnDefinition = "integer default 1")
    private Integer status;
}
