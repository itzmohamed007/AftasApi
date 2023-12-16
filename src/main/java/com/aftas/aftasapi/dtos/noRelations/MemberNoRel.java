package com.aftas.aftasapi.dtos.noRelations;

import com.aftas.aftasapi.enums.IdentityDocumentType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberNoRel {
    private Integer num;
    private String name;
    private String familyName;
    private LocalDate accessionDate;
    private String nationality;
    private String identityNumber;
    private IdentityDocumentType identityDocument;
}