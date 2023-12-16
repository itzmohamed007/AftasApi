package com.aftas.aftasapi.dtos.competitions;

import com.aftas.aftasapi.dtos.noRelations.CompetitionNoRol;
import com.aftas.aftasapi.dtos.noRelations.MemberNoRol;
import lombok.Data;

@Data
public class CompetitionRanking {
    private MemberNoRol member; // No relations member dto
    private Integer rank = 0;
    private Integer score = 0;
}
