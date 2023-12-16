package com.aftas.aftasapi.dtos;

import com.aftas.aftasapi.enums.IdentityDocumentType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReqMember {
    private Integer num;
    @NotNull(message = "name cannot be null")
    private String name;
    @NotNull(message = "family name cannot be null")
    private String familyName;
    private LocalDate accessionDate = LocalDate.now(); // making sure that accession date will always be the current day date
    @NotNull(message = "nationality cannot be null")
    private String nationality;
    @NotNull(message = "identity number cannot be null")
    private String identityNumber;
    @NotNull(message = "identity document type cannot be null")
    private String identityDocument;
}
