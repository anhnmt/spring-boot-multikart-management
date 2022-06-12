package com.example.multikart.domain.model;

import com.example.multikart.domain.dto.WardDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wards")
public class Ward extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ward_id")
    private Long wardId;

    private Long districtId;

    @NotBlank
    private String name;

    private String type;

    public Ward(WardDTO ward, Long proId) {
        wardId = ward.getLevel3Id();
        districtId = proId;
        name = ward.getName();
        type = ward.getType();
    }
}
