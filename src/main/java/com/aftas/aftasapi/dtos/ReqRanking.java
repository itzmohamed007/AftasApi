package com.aftas.aftasapi.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReqRanking {
    @NotNull(message = "member number cannot be null")
    private Integer memberNumber;
    @NotNull(message = "competition code cannot be null")
    private String competitionCode;
    private Integer rank;
    private Integer score;
}
