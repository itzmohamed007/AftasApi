package com.aftas.aftasapi.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Ranking {
    @EmbeddedId
    private CompetitionMember id;
    private Integer rank;
    private Integer score;
    @ManyToOne
    private Competition competition;
    @ManyToOne
    private Member member;
}
