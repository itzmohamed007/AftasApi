package com.aftas.aftasapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class CompetitionMember {
    @Column(nullable = false, name = "member_num")
    private Integer memberNumber;
    @Column(nullable = false, name = "competition_code")
    private String competitionCode;

}
