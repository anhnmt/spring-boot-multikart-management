package com.example.multikart.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WardDTO {
    @SerializedName("level3_id")
    private Long level3Id;
    private String name;
    private String type;
}
