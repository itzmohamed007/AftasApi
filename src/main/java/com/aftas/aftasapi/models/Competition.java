package com.aftas.aftasapi.models;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class Competition {
    private String code;
    private Date date;
    private Time startTime;
    private Time endTime;
    private Integer numberOfParticipants;
    private String location;
    private Double amount;
}
