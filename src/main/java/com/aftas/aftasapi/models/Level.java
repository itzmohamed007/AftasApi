package com.aftas.aftasapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Level {
    @Id
    private Integer code;
    private String description;
    private Integer points;
    @OneToMany
    Set<Fish> fish = new HashSet<>();
}
