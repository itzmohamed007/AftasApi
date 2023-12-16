package com.aftas.aftasapi.controllers.interfaces;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface ICompetitionController<X, Y, Z> {
    public ResponseEntity<Y> read(Z z);
    public ResponseEntity<List<Y>> readAll(String filter);
    public ResponseEntity<Page<Y>> readAllPaginated(String filter, Pageable pageable);
    public ResponseEntity<Y> create(X x);
    public ResponseEntity<Y> update(X x, Z z);
    public ResponseEntity<Map<String, String>> delete(Z z);
}
