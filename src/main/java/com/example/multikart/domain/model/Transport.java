package com.example.multikart.domain.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transports")
public class Transport extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transport_id")
    private Long transportId;

    @NotBlank
    private String name;

    // Mô tả, ghi chú
    private String description;

    // Trạng thái
    @Column(name = "status", columnDefinition = "integer default 1", nullable = false)
    private Integer status;
}
