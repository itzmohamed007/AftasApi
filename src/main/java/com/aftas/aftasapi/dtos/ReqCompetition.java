package com.aftas.aftasapi.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ReqCompetition {
    private String code;
//    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "date cannot be null")
    @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$", message = "Invalid date format. Use dd-MM-yyyy")
    private String date;
    @NotNull(message = "start time cannot be null")
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$", message = "Invalid time format. Use HH:mm")
    private String startTime;
    @NotNull(message = "end time cannot be null")
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$", message = "Invalid time format. Use HH:mm")
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
