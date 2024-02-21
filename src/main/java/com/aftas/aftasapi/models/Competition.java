package com.aftas.aftasapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Competition {
    @Id
    @Column(unique = true, nullable = false)
    private String code;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer numberOfParticipants;
    private String location;
    private Double amount;
    @OneToMany(mappedBy = "competition", fetch = FetchType.LAZY)
    private List<Ranking> rankings;
    @OneToMany(mappedBy = "competition", fetch = FetchType.LAZY)
    private List<Hunting> hunting;
}
