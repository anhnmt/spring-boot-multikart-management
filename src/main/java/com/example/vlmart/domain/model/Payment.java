package com.example.vlmart.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
public class Payment extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_id")
    private Long paymentId;

    @NotBlank
    private String name;

    private String description;

    // Trạng thái
    @Column(columnDefinition = "integer default 1")
    private Integer status;
}
