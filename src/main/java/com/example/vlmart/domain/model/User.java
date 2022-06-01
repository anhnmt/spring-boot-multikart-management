package com.example.vlmart.domain.model;

import com.example.vlmart.domain.dto.CreateUserRequestDTO;
import com.example.vlmart.domain.dto.UpdateUserRequestDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "role_id", nullable = false)
    private Long roleId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    // Trạng thái
    @Column(name = "status", columnDefinition = "integer default 1", nullable = false)
    private Integer status;

    public User(CreateUserRequestDTO input) {
        name = input.getName();
        email = input.getEmail();
        password = input.getPassword();
        roleId = input.getRoleId();
        status = input.getStatus();
    }

    public User(UpdateUserRequestDTO input) {
        name = input.getName();
        email = input.getEmail();
        password = input.getPassword();
        roleId = input.getRoleId();
        status = input.getStatus();
    }
}
