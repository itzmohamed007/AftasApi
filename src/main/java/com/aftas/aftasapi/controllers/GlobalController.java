package com.aftas.aftasapi.controllers;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// X => request dto
// Y => response dto
// Z => primary key type
public interface GlobalController<X, Y, Z> {
    @GetMapping("/{z}")
    public ResponseEntity<X> read(@PathVariable Z z);
    @GetMapping("/all")
    public ResponseEntity<List<Y>> readAll();
    @GetMapping
    public ResponseEntity<Page<Y>> readAllPaginated(Pageable pageable);
    @PostMapping
    public ResponseEntity<Y> create(@Valid @RequestBody X x);
    @PutMapping("/{z}")
    public ResponseEntity<Y> update(@Valid @RequestBody X x, @PathVariable Z z);
    @DeleteMapping("{z}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Z z);
}
