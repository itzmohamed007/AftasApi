package com.aftas.aftasapi.dtos;

import com.aftas.aftasapi.models.Level;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResFish {
    private String name;
    private Double averageWeight;
    private ResLevel level;
}
