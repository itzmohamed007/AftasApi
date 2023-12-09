package com.aftas.aftasapi.models;

import jakarta.persistence.EmbeddedId;
import lombok.Data;

@Data
public class Ranking {
    @EmbeddedId
    private CompetitionMember id;
    private Integer rank;
    private Integer score;
}
