package com.aftas.aftasapi.dtos;

import com.aftas.aftasapi.models.Hunting;
import com.aftas.aftasapi.models.Ranking;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class ReqCompetition {
    @NotNull(message = "code cannot be null")
    private String code;
    @NotNull(message = "date cannot be null")
    private LocalDate date;
    @NotNull(message = "start time cannot be null")
    private LocalTime startTime;
    @NotNull(message = "end time cannot be null")
    private LocalTime endTime;
    @Min(value = 10, message = "number of participants cannot be inferior than 10")
    @NotBlank(message = "number of participants cannot be blank")
    private Integer numberOfParticipants;
    @NotNull(message = "location cannot be null")
    private String location;
    @Min(value = 100, message = "amount cannot be inferior than 100")
    @NotBlank(message = "amount cannot be blank")
    private Double amount;
}
