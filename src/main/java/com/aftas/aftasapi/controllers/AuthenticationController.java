package com.aftas.aftasapi.controllers;

import com.aftas.aftasapi.dtos.noRelations.LoginDto;
import com.aftas.aftasapi.dtos.noRelations.RegisterDto;
import com.aftas.aftasapi.services.imp.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
@CrossOrigin("http://localhost:4200")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDto registerDto) {
        return new ResponseEntity<>(Map.of("token", authenticationService.register(registerDto)), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(Map.of("token", authenticationService.login(loginDto)), HttpStatus.OK);
    }
}
