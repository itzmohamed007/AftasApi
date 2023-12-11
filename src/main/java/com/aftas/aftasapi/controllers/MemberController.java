package com.aftas.aftasapi.controllers;

import com.aftas.aftasapi.dtos.ReqMember;
import com.aftas.aftasapi.dtos.ResMember;
import com.aftas.aftasapi.services.IMemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController implements GlobalController<ReqMember, ResMember, Integer>{
    private final IMemberService service;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ResMember> read(@PathVariable Integer id) {
        return new ResponseEntity<>(service.read(id), HttpStatus.OK);
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<ResMember>> readAll() {
        return new ResponseEntity<>(service.readAll(), HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<ResMember>> readAllPaginated(Pageable pageable) {
        return new ResponseEntity<>(service.readAllPaginated(pageable), HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<ResMember> create(@Valid @RequestBody ReqMember reqMember) {
        return new ResponseEntity<>(service.create(reqMember), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ResMember> update(@Valid @RequestBody ReqMember reqMember, @PathVariable Integer id) {
        return new ResponseEntity<>(service.update(reqMember, id), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<>(Map.of("message", "Member created Successfully"), HttpStatus.OK);
    }
}
