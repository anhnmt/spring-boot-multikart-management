package com.example.vlmart.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
public class Unit extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long unitId;

    @NotBlank
    private String name;

    private String description;
}
