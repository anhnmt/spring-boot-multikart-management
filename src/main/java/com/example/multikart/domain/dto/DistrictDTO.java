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
    @SerializedName("level2_id")
    private Long level2Id;
    private String name;
    private String type;
    private List<WardDTO> level3s;
}
