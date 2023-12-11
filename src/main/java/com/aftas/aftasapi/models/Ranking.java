package com.aftas.aftasapi.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ranking {
    @EmbeddedId
    private RankingId id;
    private Integer rank;
    private Integer score;
    @ManyToOne
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;
    @ManyToOne
    @JoinColumn(name = "competition_code")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Competition competition;
}
