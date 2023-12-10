package com.aftas.aftasapi.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Hunting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer numberOfFish;
    @ManyToOne
    private Member member;
    @ManyToOne
    private Competition competition;
    @ManyToOne
    private Fish fish;
}
