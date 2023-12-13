package com.aftas.aftasapi.dtos;

import com.aftas.aftasapi.models.Competition;
import com.aftas.aftasapi.models.Member;
import lombok.Data;

@Data
public class ResRanking {
    private ResMember member;
    private ResCompetition competition;
    private Integer rank = 0;
    private Integer score = 0;
}
