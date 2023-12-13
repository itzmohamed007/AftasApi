package com.aftas.aftasapi.dtos;

import com.aftas.aftasapi.models.Hunting;
import com.aftas.aftasapi.models.Ranking;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private List<Ranking> rankings;
    @JsonIgnore
    private List<Hunting> hunting;
}
