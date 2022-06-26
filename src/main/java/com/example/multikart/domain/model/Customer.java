package com.example.multikart.domain.model;

import com.example.multikart.domain.dto.CustomerRequestDTO;
import com.example.multikart.domain.dto.UserProfileRequestDTO;
import com.example.multikart.domain.dto.UserRegisterRequestDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer extends BaseModel implements Serializable {
    private static final long serialVersionUID = -1813145955899712226L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    private String password;

    private String provinceId;
    private String districtId;
    private String wardId;
    private String address;

    private String avatar;

    // Trạng thái
    @Column(name = "status", columnDefinition = "integer default 1", nullable = false)
    private Integer status;

    public Customer(CustomerRequestDTO input) {
        name = input.getName();
        email = input.getEmail();
        phone = input.getPhone();
        password = input.getPassword();
        status = input.getStatus();
    }

    public Customer(UserProfileRequestDTO input) {
        name = input.getName();
        email = input.getEmail();
        phone = input.getPhone();
        password = input.getPassword();
    }

    public Customer(UserRegisterRequestDTO input) {
        name = input.getName();
        email = input.getEmail();
        password = input.getPassword();
    }
}
