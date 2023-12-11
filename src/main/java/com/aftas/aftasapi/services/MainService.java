package com.aftas.aftasapi.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

// X => request dto
// Y => response dto
// Z => primary key type
public interface MainService<X, Y, Z> {
    Y read(Z z);
    List<Y> readAll();
    Page<Y> readAllPaginated(Pageable pageable);
    Y create(X x);
    Y update(X x, Integer id);
    void delete(Z z);
}
