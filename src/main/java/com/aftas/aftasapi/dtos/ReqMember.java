package com.aftas.aftasapi.dtos;

import com.aftas.aftasapi.enums.IdentityDocumentType;
import com.aftas.aftasapi.models.Hunting;
import com.aftas.aftasapi.models.Ranking;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ReqMember {
    private Integer id;
    private String name;
    private String familyName;
    private LocalDate accessionDate;
    private String nationality;
    private String identityNumber;
    private IdentityDocumentType identityDocument;
}
