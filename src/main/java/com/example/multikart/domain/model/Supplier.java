package com.example.multikart.domain.model;

import com.example.multikart.domain.dto.SupplierRequestDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "suppliers")
public class Supplier extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private Long supplierId;

    @NotBlank
    private String name;

    private String email;
    private String phone;

    private String website;

    @NotBlank
    private String address;

    @Column(columnDefinition = "text")
    private String description;
    private String taxCode;

    // Trạng thái
    @Column(name = "status", columnDefinition = "integer default 1", nullable = false)
    private Integer status;

    public Supplier(SupplierRequestDTO input) {
        name = input.getName();
        email = input.getEmail();
        phone = input.getPhone();
        website = input.getWebsite();
        address = input.getAddress();
        taxCode = input.getTaxCode();
        status = input.getStatus();
    }

}
