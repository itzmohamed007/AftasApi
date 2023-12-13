package com.aftas.aftasapi.dtos;

import com.aftas.aftasapi.enums.IdentityDocumentType;
import com.aftas.aftasapi.models.Hunting;
import com.aftas.aftasapi.models.Ranking;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ResMember {
    private Integer num;
    private String name;
    private String familyName;
    private LocalDate accessionDate;
    private String nationality;
    private String identityNumber;
    private IdentityDocumentType identityDocument;
    @JsonIgnore
    private List<Ranking> rankings;
    @JsonIgnore
    private List<Hunting> hunting;
}
