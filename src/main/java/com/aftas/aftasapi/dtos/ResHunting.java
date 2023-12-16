package com.aftas.aftasapi.dtos;

import com.aftas.aftasapi.dtos.noRelations.CompetitionNoRel;
import com.aftas.aftasapi.dtos.noRelations.FishNoRel;
import com.aftas.aftasapi.dtos.noRelations.MemberNoRel;
import lombok.Data;

@Data
public class ResHunting {
    private Integer id;
    private Integer numberOfFish;
    private Float fishWeight;
    private FishNoRel fish;
    private MemberNoRel member;
    private CompetitionNoRel competition;
}
