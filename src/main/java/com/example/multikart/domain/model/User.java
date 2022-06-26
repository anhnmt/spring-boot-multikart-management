package com.example.multikart.domain.model;

import com.example.multikart.domain.dto.UserRequestDTO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseModel implements Serializable {
    private static final long serialVersionUID = -18131459558712226L;

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

    private String avatar;

    // Trạng thái
    @Column(name = "status", columnDefinition = "integer default 1", nullable = false)
    private Integer status;

    public User(UserRequestDTO input) {
        name = input.getName();
        email = input.getEmail();
        password = input.getPassword();
        roleId = input.getRoleId();
        status = input.getStatus();
    }
}
