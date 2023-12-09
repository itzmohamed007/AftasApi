package com.aftas.aftasapi.models;

import com.aftas.aftasapi.enums.IdentityDocumentType;
import lombok.Data;

import java.util.Date;

@Data
public class Member {
    private Integer number;
    private String name;
    private String familyName;
    private Date accessionDate;
    private String nationality;
    private IdentityDocumentType identityDocument;
    private String identityNumber;
}
