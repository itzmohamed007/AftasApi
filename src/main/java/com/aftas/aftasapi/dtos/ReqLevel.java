package com.aftas.aftasapi.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReqLevel {
    private Integer id;
    @NotNull(message = "description cannot be null")
    private String description;
    @Min(value = 1, message = "points cannot be less than 1")
    @NotNull(message = "points cannot be null")
    private Integer points;
}
