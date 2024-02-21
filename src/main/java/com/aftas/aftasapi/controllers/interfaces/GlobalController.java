package com.aftas.aftasapi.controllers.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

// X => request dto
// Y => response dto
// Z => primary key type
public interface GlobalController<X, Y, Z> {
    public ResponseEntity<Y> read(Z z);
    public ResponseEntity<List<Y>> readAll();
    public ResponseEntity<Page<Y>> readAllPaginated(Pageable pageable);
    public ResponseEntity<Y> create(X x);
    public ResponseEntity<Y> update(X x, Z z);
    public ResponseEntity<Map<String, String>> delete(Z z);
}
