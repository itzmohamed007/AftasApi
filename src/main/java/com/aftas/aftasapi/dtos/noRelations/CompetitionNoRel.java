package com.aftas.aftasapi.dtos.noRelations;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CompetitionNoRel {
    private String code;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer numberOfParticipants;
    private String location;
    private Double amount;
}
