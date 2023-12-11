package com.aftas.aftasapi.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ResLevel {
    private Integer id;
    private String description;
    private Integer points;
    private List<ResFish> fishes;
}
