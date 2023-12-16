package com.aftas.aftasapi.dtos;

import com.aftas.aftasapi.dtos.noRelations.CompetitionNoRol;
import com.aftas.aftasapi.dtos.noRelations.MemberNoRol;
import lombok.Data;

@Data
public class ResRanking {
    private MemberNoRol member; // No relations member dto
    private CompetitionNoRol competition; // No Relations Member
    private Integer rank = 0;
    private Integer score = 0;
}
