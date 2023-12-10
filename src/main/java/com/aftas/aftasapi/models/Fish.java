package com.aftas.aftasapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Fish {
    @Id
    private String name;
    private Double averageWeight;
    @ManyToOne
    private Level level;
    @OneToMany
    private Set<Hunting> hunting = new HashSet<>();
}