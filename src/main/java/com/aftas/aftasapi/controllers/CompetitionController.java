package com.aftas.aftasapi.controllers;

import com.aftas.aftasapi.dtos.ReqCompetition;
import com.aftas.aftasapi.dtos.ResCompetition;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/competitions")
@RequiredArgsConstructor
public class CompetitionController implements GlobalController<ReqCompetition, ResCompetition, String> {

    @Override
    @GetMapping("/{code}")
    public ResponseEntity<ResCompetition> read(@PathVariable String code) {
        return null;
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<ResCompetition>> readAll() {
        return null;
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<ResCompetition>> readAllPaginated(Pageable pageable) {
        return null;
    }

    @Override
    @PostMapping
    public ResponseEntity<ResCompetition> create(@Valid @RequestBody ReqCompetition reqCompetition) {
        return null;
    }

    @Override
    @PutMapping("/{code}")
    public ResponseEntity<ResCompetition> update(@Valid @RequestBody ReqCompetition reqCompetition, @PathVariable String code) {
        return null;
    }

    @Override
    @DeleteMapping("/{code}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable String code) {
        return null;
    }
}
