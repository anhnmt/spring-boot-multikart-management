package com.example.multikart.domain.model;

import com.example.multikart.domain.dto.UnitRequestDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "units", indexes = @Index(columnList = "status"))
public class Unit extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unit_id")
    private Long unitId;

    @NotBlank
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    // Trạng thái
    @Column(name = "status", columnDefinition = "integer default 1", nullable = false)
    private Integer status;

    public Unit(UnitRequestDTO input) {
        name = input.getName();
        status = input.getStatus();
    }
}
