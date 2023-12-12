package com.aftas.aftasapi.controllers;

import com.aftas.aftasapi.dtos.ReqCompetition;
import com.aftas.aftasapi.dtos.ResCompetition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/competitions")
@RequiredArgsConstructor
public class CompetitionController implements GlobalController<ReqCompetition, ResCompetition, String> {

    @Override
    public ResponseEntity<ResCompetition> read(String s) {
        return null;
    }

    @Override
    public ResponseEntity<List<ResCompetition>> readAll() {
        return null;
    }

    @Override
    public ResponseEntity<Page<ResCompetition>> readAllPaginated(Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<ResCompetition> create(ReqCompetition reqCompetition) {
        return null;
    }

    @Override
    public ResponseEntity<ResCompetition> update(ReqCompetition reqCompetition, String s) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, String>> delete(String s) {
        return null;
    }
}
