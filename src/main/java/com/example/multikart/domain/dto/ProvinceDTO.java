package com.example.multikart.domain.dto;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProvinceDTO {
    @SerializedName("level1_id")
    private Long level1Id;
    private String name;
    private String type;
    private List<DistrictDTO> level2s;
}
