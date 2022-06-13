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
    @SerializedName("code")
    private Long provinceId;

    private String name;
    private String type;
    private String slug;

    @SerializedName("name_with_type")
    private String nameWithType;

    private List<DistrictDTO> data;
}
