package com.aftas.aftasapi.repositories;

import com.aftas.aftasapi.models.Competition;
import com.aftas.aftasapi.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, String> {

}