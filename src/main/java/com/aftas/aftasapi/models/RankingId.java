package com.aftas.aftasapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RankingId implements Serializable {
    @Column(name = "competition_code")
    private String competitionCode;
    @Column(name = "member_num")
    private Integer memberNum;
}
