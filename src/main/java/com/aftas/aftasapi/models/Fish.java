package com.aftas.aftasapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Fish {
    @Id
    private String name;
    private Double averageWeight;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "level_id")
    private Level level;
    @OneToMany(mappedBy = "fish", fetch = FetchType.LAZY)
    private List<Hunting> hunting;
}
