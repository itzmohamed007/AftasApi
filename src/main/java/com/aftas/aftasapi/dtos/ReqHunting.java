package com.aftas.aftasapi.dtos;

import com.aftas.aftasapi.models.Competition;
import com.aftas.aftasapi.models.Fish;
import com.aftas.aftasapi.models.Member;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
public class ReqHunting {
    private Integer id;
//    @NotNull(message = "number of fishes cannot be null")
//    @Min(value = 1, message = "number of fishes cannot be negative")
    private Integer numberOfFish;
    @NotNull(message = "fish weight cannot be null")
    @Min(value = 10, message = "minimum fish weight cannot be less than 10g")
    private Float fishWeight;
    @NotNull(message = "fish cannot be null")
    private String fish;
    @NotNull(message = "member cannot be null")
    private Integer member;
    @NotNull(message = "competition cannot be null")
    private String competition;
}
