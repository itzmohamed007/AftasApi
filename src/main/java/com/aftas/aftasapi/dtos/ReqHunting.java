package com.aftas.aftasapi.dtos;

import com.aftas.aftasapi.models.Competition;
import com.aftas.aftasapi.models.Fish;
import com.aftas.aftasapi.models.Member;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
public class ReqHunting {
    private Integer id;
    private Integer numberOfFish;
    private Float fishWeight;
    private String fish;
    private Integer member;
    private String competition;
}
