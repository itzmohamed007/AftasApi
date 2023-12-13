package com.aftas.aftasapi.controllers;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

// X => request dto
// Y => response dto
// Z => primary key type
public interface GlobalController<X, Y, Z> {
    public ResponseEntity<Y> read(@PathVariable Z z);
    public ResponseEntity<List<Y>> readAll();
    public ResponseEntity<Page<Y>> readAllPaginated(Pageable pageable);
    public ResponseEntity<Y> create(@Valid @RequestBody X x);
    public ResponseEntity<Y> update(X x, Z z);
    public ResponseEntity<Map<String, String>> delete(@PathVariable Z z);
}
