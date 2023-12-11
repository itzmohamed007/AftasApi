package com.aftas.aftasapi.dtos;

import com.aftas.aftasapi.enums.IdentityDocumentType;
import com.aftas.aftasapi.models.Hunting;
import com.aftas.aftasapi.models.Ranking;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ResMember {
    private Integer id;
    private String name;
    private String familyName;
    private LocalDate accessionDate;
    private String nationality;
    private String identityNumber;
    private IdentityDocumentType identityDocument;
    private List<Ranking> rankings;
    private List<Hunting> hunting;
}
