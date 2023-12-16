package com.aftas.aftasapi.dtos;

import com.aftas.aftasapi.models.Competition;
import com.aftas.aftasapi.models.Member;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ReqRanking {
    @NotNull(message = "member cannot be null")
    private Integer member;
    @NotNull(message = "competition cannot be null")
    @Pattern(regexp = "^[a-zA-Z]{3}-\\d{2}-\\d{2}-\\d{2}$", message = "competition code format is incorrect")
    private String competition;
    private Integer rank;
    private Integer score;
}

