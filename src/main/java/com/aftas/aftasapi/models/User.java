package com.aftas.aftasapi.models;

import com.aftas.aftasapi.enums.IdentityDocumentType;
import com.aftas.aftasapi.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer num;
    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    private String familyName;
    private LocalDate accessionDate;
    private String nationality;
    @Column(unique = true)
    private String identityNumber;
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocument;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean locked;
    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    private List<Ranking> rankings;
    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    private List<Hunting> hunting;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.getRole().toString()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
