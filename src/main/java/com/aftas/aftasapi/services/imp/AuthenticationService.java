package com.aftas.aftasapi.services.imp;

import com.aftas.aftasapi.dtos.ResMember;
import com.aftas.aftasapi.dtos.noRelations.LoginDto;
import com.aftas.aftasapi.dtos.noRelations.RegisterDto;
import com.aftas.aftasapi.enums.IdentityDocumentType;
import com.aftas.aftasapi.enums.Role;
import com.aftas.aftasapi.exceptions.DocumentTypeViolationException;
import com.aftas.aftasapi.exceptions.UniqueConstraintViolationException;
import com.aftas.aftasapi.models.User;
import com.aftas.aftasapi.repositories.UserRepository;
import com.aftas.aftasapi.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationProvider authenticationProvider;

    public User register(RegisterDto registerDto) {
        try {
            System.out.println("fslkdfj");
            System.out.println(IdentityDocumentType.valueOf(registerDto.getIdentityDocument().toString()));
//            System.out.println(Role.valueOf(registerDto.getRole()));
            System.out.println(registerDto.getRole());
            User userToCreate = User.builder()
                .name(registerDto.getName())
                .familyName(registerDto.getFamilyName())
                .email(registerDto.getEmail())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .nationality(registerDto.getNationality())
                .identityNumber(registerDto.getIdentityNumber())
                .identityDocument(IdentityDocumentType.valueOf(registerDto.getIdentityDocument().toString()))
                .accessionDate(LocalDate.now())
                .role(Role.valueOf(registerDto.getRole()))
                .locked(true)
                .build();

            return userRepository.save(userToCreate);
        } catch (DataIntegrityViolationException e) {
            throw new UniqueConstraintViolationException("Violated unique constraint (document number)");
        } catch ( IllegalArgumentException e) {
            throw new DocumentTypeViolationException("Violated Enum Values Role or Document Type");
        }
    }

    public String login(LoginDto loginDto) {
        var user = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        var userDetails = (User) user.getPrincipal();
        return jwtService.generateToken(userDetails, Map.of("name", userDetails.getName() + " " + userDetails.getFamilyName()));
    }
}
