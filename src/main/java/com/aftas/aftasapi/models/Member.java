package com.aftas.aftasapi.models;

import com.aftas.aftasapi.enums.IdentityDocumentType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer num;
    private String name;
    private String familyName;
    private LocalDate accessionDate;
    private String nationality;
    @Column(unique = true)
    private String identityNumber;
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocument;
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Ranking> rankings;
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Hunting> hunting;
}
