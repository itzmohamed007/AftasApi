package com.aftas.aftasapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class CompetitionMember implements Serializable {
    @Column(nullable = false, name = "member_num")
    private Integer memberNumber;
    @Column(nullable = false, name = "competition_code")
    private String competitionCode;

}
