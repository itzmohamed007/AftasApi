package com.aftas.aftasapi.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Competition {
    @Id
    private String code;
    private Date date;
    private Time startTime;
    private Time endTime;
    private Integer numberOfParticipants;
    private String location;
    private Double amount;
    @ManyToMany()
    @JoinTable(
        name = "ranking",
        joinColumns = @JoinColumn(name = "competition_code", referencedColumnName = "competition_code"),
        inverseJoinColumns = @JoinColumn(name = "member_id", referencedColumnName = "member_num")
    )
    private Set<Member> members = new HashSet<>();
    @OneToMany
    private Set<Hunting> hunting = new HashSet<>();
    @OneToMany
    private Set<Ranking> rankings = new HashSet<>();
}
