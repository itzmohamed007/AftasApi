package com.aftas.aftasapi.dtos.competitions;

import com.aftas.aftasapi.dtos.noRelations.MemberNoRel;
import lombok.Data;

@Data
public class CompetitionRanking {
    private MemberNoRel member; // No relations member dto
    private Integer rank = 0;
    private Integer score = 0;
}
