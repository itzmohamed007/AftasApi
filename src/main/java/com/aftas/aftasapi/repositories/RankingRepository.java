package com.aftas.aftasapi.repositories;

import com.aftas.aftasapi.models.Competition;
import com.aftas.aftasapi.models.Ranking;
import com.aftas.aftasapi.models.RankingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, RankingId> {
    List<Ranking> findAllByCompetitionOrderByScoreDesc(Competition competition);
}
