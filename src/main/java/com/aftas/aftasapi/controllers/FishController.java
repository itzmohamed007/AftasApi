package com.aftas.aftasapi.controllers;

import com.aftas.aftasapi.dtos.ReqFish;
import com.aftas.aftasapi.dtos.ResFish;
import com.aftas.aftasapi.services.imp.FishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/fishes")
@RequiredArgsConstructor
public class FishController implements GlobalController<ReqFish, ResFish, String> {
    private final FishService service;

    @Override
    @GetMapping("/{name}")
    public ResponseEntity<ResFish> read(@PathVariable String name) {
        return new ResponseEntity<>(service.read(name), HttpStatus.OK);
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<ResFish>> readAll() {
        return new ResponseEntity<>(service.readAll(), HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<ResFish>> readAllPaginated(Pageable pageable) {
        return new ResponseEntity<>(service.readAllPaginated(pageable), HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<ResFish> create(@Valid @RequestBody ReqFish reqFish) {
        return new ResponseEntity<>(service.create(reqFish), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{name}")
    public ResponseEntity<ResFish> update(@Valid @RequestBody ReqFish reqFish, @PathVariable String name) {
        return new ResponseEntity<>(service.update(reqFish, name), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{name}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable String name) {
        service.delete(name);
        return new ResponseEntity<>(Map.of("message", "Fish deleted successfully"), HttpStatus.OK);
    }
}
