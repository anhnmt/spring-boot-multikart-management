package com.example.multikart.domain.dto;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WardDTO {
    @SerializedName("code")
    private Long wardId;

    @SerializedName("parent_code")
    private Long districtId;

    private String name;
    private String type;
    private String slug;
}
