package com.aftas.aftasapi.dtos;

import lombok.Data;

@Data
public class ResFish {
    private String name;
    private Double averageWeight;
    private ResLevel level;
}
