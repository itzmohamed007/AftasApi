package com.aftas.aftasapi.services.imp;

import com.aftas.aftasapi.dtos.noRelations.LoginDto;
import com.aftas.aftasapi.dtos.noRelations.RegisterDto;
import com.aftas.aftasapi.models.User;
import com.aftas.aftasapi.repositories.UserRepository;
import com.aftas.aftasapi.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationProvider authenticationProvider;

    public User register(RegisterDto registerDto) {
        User userToCreate = User.builder()
                .name(registerDto.getName())
                .familyName(registerDto.getFamilyName())
                .email(registerDto.getEmail())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .nationality(registerDto.getNationality())
                .identityNumber(registerDto.getIdentityNumber())
                .identityDocument(registerDto.getIdentityDocument())
                .accessionDate(LocalDate.now())
                .role(registerDto.getRole())
                .locked(true)
                .build();

        return userRepository.save(userToCreate);
    }

    public String login(LoginDto loginDto) {
        var user = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        var userDetails = (User) user.getPrincipal();
        return jwtService.generateToken(userDetails, Map.of("name", userDetails.getName() + " " + userDetails.getFamilyName()));
    }
}
