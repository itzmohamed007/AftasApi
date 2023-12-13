package com.aftas.aftasapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class RankingId implements Serializable {
    @Column(name = "competition_code")
    private String competitionCode;
    @Column(name = "member_num")
    private Integer memberNum;
}
