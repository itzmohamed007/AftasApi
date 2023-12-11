package com.aftas.aftasapi.controllers;

import com.aftas.aftasapi.dtos.ReqLevel;
import com.aftas.aftasapi.dtos.ResLevel;
import com.aftas.aftasapi.models.Level;
import com.aftas.aftasapi.repositories.LevelRepository;
import com.aftas.aftasapi.services.ILevelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/levels")
public class LevelController {
    private final ILevelService service;
    @GetMapping("/{id}")
    public ResponseEntity<ResLevel> read(@PathVariable Integer id) {
        return new ResponseEntity<>(service.read(id), HttpStatus.FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ResLevel>> readAll() {
        return new ResponseEntity<>(service.readAll(), HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<Page<ResLevel>> readAllPaginated(Pageable pageable) {
        return new ResponseEntity<>(service.readAllPaginated(pageable), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<ResLevel> create(@Valid @RequestBody ReqLevel reqLevel) {
        System.out.println("create methd mapped");
        return new ResponseEntity<>(service.create(reqLevel), HttpStatus.CREATED);
    }
}
