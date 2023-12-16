package com.aftas.aftasapi.controllers;

import com.aftas.aftasapi.controllers.interfaces.GlobalController;
import com.aftas.aftasapi.dtos.ReqLevel;
import com.aftas.aftasapi.dtos.ResLevel;
import com.aftas.aftasapi.services.ILevelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/levels")
public class LevelController implements GlobalController<ReqLevel, ResLevel, Integer> {
    private final ILevelService service;

    @Override
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
        return new ResponseEntity<>(service.create(reqLevel), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResLevel> update(@Valid @RequestBody ReqLevel reqLevel, @PathVariable Integer id) {
        return new ResponseEntity<>(service.update(reqLevel, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<>(Map.of("message", "Level deleted successfully"), HttpStatus.OK);
    }
}
