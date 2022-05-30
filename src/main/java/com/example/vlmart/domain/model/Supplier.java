package com.example.vlmart.domain.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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

    @NotBlank
    private String email;

    @NotBlank
    private String website;

    @NotBlank
    private String address;

    private String description;
    private String taxCode;

    // Trạng thái
    @Column(name = "status")
    @ColumnDefault(value = "1")
    private Integer status;
}
