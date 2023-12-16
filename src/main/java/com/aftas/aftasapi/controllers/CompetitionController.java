package com.aftas.aftasapi.controllers;

import com.aftas.aftasapi.controllers.interfaces.GlobalController;
import com.aftas.aftasapi.controllers.interfaces.ICompetitionController;
import com.aftas.aftasapi.dtos.ReqCompetition;
import com.aftas.aftasapi.dtos.ResCompetition;
import com.aftas.aftasapi.services.ICompetitionService;
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
@RequestMapping("/api/competitions")
@RequiredArgsConstructor
public class CompetitionController implements ICompetitionController<ReqCompetition, ResCompetition, String> {
    private final ICompetitionService service;

    @Override
    @GetMapping("/{code}")
    public ResponseEntity<ResCompetition> read(@PathVariable final String code) {
        return new ResponseEntity<>(service.read(code), HttpStatus.OK);
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<ResCompetition>> readAll(@RequestParam(name = "filter", required = false) String filter) {
        return new ResponseEntity<>(service.readAllFiltered(filter), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<ResCompetition>> readAllPaginated(@RequestParam(name = "filter", required = false) String filter, Pageable pageable) {
        return new ResponseEntity<>(service.readAllFilteredPaginated(pageable, filter), HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<ResCompetition> create(@Valid @RequestBody ReqCompetition reqCompetition) {
        return new ResponseEntity<>(service.create(reqCompetition), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{code}")
    public ResponseEntity<ResCompetition> update(@Valid @RequestBody ReqCompetition reqCompetition, @PathVariable String code) {
        return new ResponseEntity<>(service.update(reqCompetition, code), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{code}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable String code) {
        service.delete(code);
        return new ResponseEntity<>(Map.of("message", "competition deleted successfully"), HttpStatus.OK);
    }
}
