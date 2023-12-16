package com.aftas.aftasapi.dtos;

import com.aftas.aftasapi.dtos.noRelations.CompetitionNoRel;
import com.aftas.aftasapi.dtos.noRelations.MemberNoRel;
import lombok.Data;

@Data
public class ResRanking {
    private MemberNoRel member; // No relations member dto
    private CompetitionNoRel competition; // No Relations Member
    private Integer rank = 0;
    private Integer score = 0;
}
