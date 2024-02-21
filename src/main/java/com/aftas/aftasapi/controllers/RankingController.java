package com.aftas.aftasapi.controllers;

import com.aftas.aftasapi.controllers.interfaces.GlobalController;
import com.aftas.aftasapi.dtos.ReqRanking;
import com.aftas.aftasapi.dtos.ResRanking;
import com.aftas.aftasapi.models.RankingId;
import com.aftas.aftasapi.services.IRankingService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rankings")
@CrossOrigin("http://localhost:4200")
public class RankingController implements GlobalController<ReqRanking, ResRanking, RankingId> {
    private final IRankingService service;

    @Override
    @PostMapping("/find")
    public ResponseEntity<ResRanking> read(@RequestBody @Valid RankingId id) {
        return new ResponseEntity<>(service.read(id), HttpStatus.OK);
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<ResRanking>> readAll() {
        return new ResponseEntity<>(service.readAll(), HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<ResRanking>> readAllPaginated(Pageable pageable) {
        return new ResponseEntity<>(service.readAllPaginated(pageable), HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<ResRanking> create(@RequestBody @Valid ReqRanking reqRanking) {
        return new ResponseEntity<>(service.create(reqRanking), HttpStatus.CREATED);
    }

    @PostMapping("/podium/{competition}")
    public ResponseEntity<List<ResRanking>> podium(@PathVariable String competition) {
        return new ResponseEntity<>(service.calcRanking(competition), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ResRanking> update(@RequestBody @Valid ReqRanking reqRanking, @PathVariable RankingId id) {
        return new ResponseEntity<>(service.update(reqRanking, id), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable RankingId id) {
        service.delete(id);
        return new ResponseEntity<>(Map.of("message", "Ranking deleted successfully"), HttpStatus.OK);
    }
}
