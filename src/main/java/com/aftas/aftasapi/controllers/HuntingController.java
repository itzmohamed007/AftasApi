package com.aftas.aftasapi.controllers;

import com.aftas.aftasapi.controllers.interfaces.GlobalController;
import com.aftas.aftasapi.dtos.ReqHunting;
import com.aftas.aftasapi.dtos.ResHunting;
import com.aftas.aftasapi.services.imp.HuntingService;
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
@RequestMapping("/api/hunts")
@RequiredArgsConstructor
public class HuntingController implements GlobalController<ReqHunting, ResHunting, Integer> {
    private final HuntingService service;
    @Override
    public ResponseEntity<ResHunting> read(Integer integer) {
        return null;
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<ResHunting>> readAll() {
        return new ResponseEntity<>(service.readAll(), HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<ResHunting>> readAllPaginated(Pageable pageable) {
        return new ResponseEntity<>(service.readAllPaginated(pageable), HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<ResHunting> create(@Valid @RequestBody ReqHunting reqHunting) {
        return new ResponseEntity<>(service.create(reqHunting), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResHunting> update(ReqHunting reqHunting, Integer integer) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, String>> delete(Integer integer) {
        return null;
    }
}
