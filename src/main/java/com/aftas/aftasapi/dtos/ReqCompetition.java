package com.aftas.aftasapi.dtos;

import com.aftas.aftasapi.models.Hunting;
import com.aftas.aftasapi.models.Ranking;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class ReqCompetition {
    private String code;
    @Temporal(TemporalType.DATE)
    @NotNull(message = "date cannot be null")
    private String date;
    @NotNull(message = "start time cannot be null")
    @Temporal(TemporalType.TIME)
    private LocalTime startTime;
    @NotNull(message = "end time cannot be null")
    @Temporal(TemporalType.TIME)
    private String endTime;
    @Min(value = 10, message = "number of participants cannot be inferior than 10")
    @NotNull(message = "number of participants cannot be null")
    private Integer numberOfParticipants;
    @NotNull(message = "location cannot be null")
    private String location;
    @Min(value = 100, message = "amount cannot be inferior than 100")
    @NotNull(message = "amount cannot be null")
    private Double amount;
}
