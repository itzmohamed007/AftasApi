package com.aftas.aftasapi.models;

import jakarta.persistence.*;
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
    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer rank;
    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer score;
    @ManyToOne
    @MapsId("memberNum")
    @JoinColumn(name = "member_num")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;
    @ManyToOne
    @MapsId("competitionCode")
    @JoinColumn(name = "competition_code")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Competition competition;
}
