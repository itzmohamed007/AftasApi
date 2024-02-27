package com.aftas.aftasapi.repositories;

import com.aftas.aftasapi.models.Competition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, String> {
    Optional<Competition> getByDate(LocalDate date);
    @Query("SELECT COUNT(*) FROM Competition c JOIN Ranking r ON c.code = r.id.competitionCode JOIN User m ON r.id.memberNum = m.num WHERE c.code = :competitionCode")

    Integer getMembersCount(String competitionCode);
    Page<Competition> findAllByDateBefore(LocalDate date, Pageable pageable);
    Page<Competition> findAllByDate(LocalDate date, Pageable pageable);
}
