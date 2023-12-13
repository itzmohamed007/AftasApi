package com.aftas.aftasapi.repositories;

import com.aftas.aftasapi.models.Ranking;
import com.aftas.aftasapi.models.RankingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, RankingId> {
}
