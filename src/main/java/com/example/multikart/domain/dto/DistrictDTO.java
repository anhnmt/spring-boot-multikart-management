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
public class DistrictDTO {
    @SerializedName("code")
    private Long districtId;

    @SerializedName("parent_code")
    private Long provinceId;

    private String name;
    private String type;
    private String slug;

    private List<WardDTO> data;
}
