package com.example.multikart.domain.model;

import com.example.multikart.domain.dto.CustomerRequestDTO;
import com.example.multikart.domain.dto.UserRegisterRequestDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    // Trạng thái
    @Column(name = "status", columnDefinition = "integer default 1", nullable = false)
    private Integer status;

    public Customer(CustomerRequestDTO input) {
        name = input.getName();
        email = input.getEmail();
        password = input.getPassword();
        status = input.getStatus();
    }

    public Customer(UserRegisterRequestDTO input) {
        name = input.getName();
        email = input.getEmail();
        password = input.getPassword();
    }
}
