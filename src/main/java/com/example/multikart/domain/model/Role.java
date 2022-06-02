package com.example.multikart.domain.model;

import com.example.multikart.domain.dto.CreateRoleRequestDTO;
import com.example.multikart.domain.dto.UpdateRoleRequestDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @NotBlank
    private String name;

    private String description;

    // Trạng thái
    @Column(name = "status", columnDefinition = "integer default 1", nullable = false)
    private Integer status;

    public Role(CreateRoleRequestDTO input) {
        name = input.getName();
        status = input.getStatus();
    }

    public Role(UpdateRoleRequestDTO input) {
        name = input.getName();
        status = input.getStatus();
    }
}
