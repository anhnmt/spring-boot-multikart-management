package com.example.multikart.domain.model;

import com.example.multikart.domain.dto.ProvinceDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "provinces")
public class Province extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "province_id")
    private Long provinceId;

    @NotBlank
    private String name;

    private String type;

    public Province(ProvinceDTO province) {
        provinceId = province.getLevel1Id();
        name = province.getName();
        type = province.getType();
    }
}
