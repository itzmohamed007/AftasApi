package com.aftas.aftasapi.dtos;

import lombok.Data;

@Data
public class ResRanking {
    private Integer memberNumber;
    private String competitionCode;
    private Integer rank;
    private Integer score;
}
