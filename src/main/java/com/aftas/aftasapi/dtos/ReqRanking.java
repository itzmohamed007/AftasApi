package com.aftas.aftasapi.dtos;

import com.aftas.aftasapi.models.Competition;
import com.aftas.aftasapi.models.Member;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReqRanking {
    @NotNull(message = "member cannot be null")
    private Integer member;

    @NotNull(message = "competition cannot be null")
    private String competition;

    private Integer rank;
    private Integer score;
}

