package com.aftas.aftasapi.models;

import com.aftas.aftasapi.enums.IdentityDocumentType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer number;
    private String name;
    private String familyName;
    private Date accessionDate;
    private String nationality;
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocument;
    private String identityNumber;
    @ManyToMany(mappedBy = "members")
    private Set<Competition> competitions = new HashSet<>();
    @OneToMany(mappedBy = "member")
    private Set<Hunting> hunting = new HashSet<>();
    @OneToMany(mappedBy = "member")
    private Set<Ranking> rankings = new HashSet<>();
}
