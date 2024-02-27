package com.aftas.aftasapi.controllers;

import com.aftas.aftasapi.models.User;
import com.aftas.aftasapi.services.imp.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("api/users")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class UserController {
    private final UserService userService;

    @GetMapping("/locked")
    public ResponseEntity<List<User>> fetchAllLockedAccounts() {
        return new ResponseEntity<>(userService.fetchAllLockedAccounts(), HttpStatus.OK);
    }
}
