package com.aftas.aftasapi.controllers;

import com.aftas.aftasapi.models.User;
import com.aftas.aftasapi.services.imp.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("api/users")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> fetchAllAccounts() {
        return new ResponseEntity<>(userService.fetchAllAccounts(), HttpStatus.OK);
    }

    @GetMapping("/locked")
    public ResponseEntity<List<User>> fetchAllLockedAccounts() {
        return new ResponseEntity<>(userService.fetchAllLockedAccounts(), HttpStatus.OK);
    }

    @PatchMapping("unlock/{num}")
    public ResponseEntity<?> unlockAccount(@PathVariable Integer num) {
        userService.unlockAccount(num);
        return new ResponseEntity<>(Map.of("message", "Account updated successfully"), HttpStatus.OK);
    }
}
