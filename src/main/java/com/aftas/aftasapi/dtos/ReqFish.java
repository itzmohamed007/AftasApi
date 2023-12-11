package com.aftas.aftasapi.dtos;

import com.aftas.aftasapi.models.Level;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReqFish {
    @NotNull(message = "name cannot be null")
    private String name;
    @NotNull(message = "average weight cannot be null")
    private Double averageWeight;
    @NotNull(message = "level cannot be null")
    private ReqLevel level;
}
