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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/fishes")
@RequiredArgsConstructor
public class FishController implements GlobalController<ReqFish, ResFish, String> {
    private final FishService service;

    @Override
    public ResponseEntity<ReqFish> read(String s) {
        return null;
    }

    @Override
    public ResponseEntity<List<ResFish>> readAll() {
        return null;
    }

    @Override
    public ResponseEntity<Page<ResFish>> readAllPaginated(Pageable pageable) {
        return null;
    }

    @Override
        public ResponseEntity<ResFish> create(@Valid @RequestBody ReqFish reqFish) {
            return new ResponseEntity<>(service.create(reqFish), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResFish> update(ReqFish reqFish, String s) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, String>> delete(String s) {
        return null;
    }
}
