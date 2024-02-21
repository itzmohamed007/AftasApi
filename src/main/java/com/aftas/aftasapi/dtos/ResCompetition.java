package com.aftas.aftasapi.dtos;

import com.aftas.aftasapi.dtos.competitions.CompetitionRanking;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class ResCompetition {
    private String code;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer numberOfParticipants;
    private String location;
    private Double amount;
    private List<CompetitionRanking> rankings;
}
