package com.example.multikart.domain.model;

import com.example.multikart.domain.dto.RoleRequestDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles", indexes = @Index(columnList = "status"))
public class Role extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @NotBlank
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    // Trạng thái
    @Column(name = "status", columnDefinition = "integer default 1", nullable = false)
    private Integer status;

    public Role(RoleRequestDTO input) {
        name = input.getName();
        status = input.getStatus();
    }
}
