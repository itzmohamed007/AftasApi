package com.aftas.aftasapi.repositories;

import com.aftas.aftasapi.models.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, String> {
    Optional<Competition> getByDate(LocalDate date);
}
