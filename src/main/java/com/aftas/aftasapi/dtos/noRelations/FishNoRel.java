package com.aftas.aftasapi.dtos.noRelations;

import com.aftas.aftasapi.models.Hunting;
import com.aftas.aftasapi.models.Level;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Data
public class FishNoRel {
    private String name;
    private Double averageWeight;
}
