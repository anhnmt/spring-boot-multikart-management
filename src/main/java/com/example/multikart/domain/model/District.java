package com.example.multikart.domain.model;

import com.example.multikart.domain.dto.DistrictDTO;
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
@Table(name = "districts")
public class District extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "district_id")
    private Long districtId;

    private Long provinceId;

    @NotBlank
    private String name;

    private String type;

    public District(DistrictDTO district, Long proId) {
        districtId = district.getLevel2Id();
        provinceId = proId;
        name = district.getName();
        type = district.getType();
    }
}
