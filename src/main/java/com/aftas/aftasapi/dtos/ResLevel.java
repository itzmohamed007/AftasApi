package com.aftas.aftasapi.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.List;

@Data
public class ResLevel {
    private Integer id;
    private String description;
    private Integer points;
    @JsonIgnore
    private List<ResFish> fishes;
}
