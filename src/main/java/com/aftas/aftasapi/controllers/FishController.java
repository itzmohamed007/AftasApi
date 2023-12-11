package com.aftas.aftasapi.controllers;

import com.aftas.aftasapi.dtos.ReqFish;
import com.aftas.aftasapi.dtos.ResFish;
import com.aftas.aftasapi.services.imp.FishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/fishes")
@RequiredArgsConstructor
public class FishController {
    private final FishService service;

    @PostMapping
    public ResponseEntity<ResFish> create(@Valid @RequestBody ReqFish reqFish) {
        return new ResponseEntity<>(service.create(reqFish), HttpStatus.CREATED);
    }
}
