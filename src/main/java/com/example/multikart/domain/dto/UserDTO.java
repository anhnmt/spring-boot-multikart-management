package com.example.multikart.domain.dto;

import com.example.multikart.domain.model.Role;
import com.example.multikart.domain.model.User;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    private static final long serialVersionUID = -1813145955899712226L;
    private Long userId;

    private Long roleId;
    private String roleName;

    private String name;

    private String email;
    private String password;
    private Integer status;

    public UserDTO(User user, Role role) {
        this.userId = user.getUserId();

        this.roleId = user.getRoleId();
        this.roleName = role.getName();

        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.status = user.getStatus();
    }
}