package com.aftas.aftasapi.repositories;

import com.aftas.aftasapi.models.Competition;
import com.aftas.aftasapi.models.Fish;
import com.aftas.aftasapi.models.Hunting;
import com.aftas.aftasapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HuntingRepository extends JpaRepository<Hunting, Integer> {
    Optional<Hunting> findHuntingByCompetitionAndMemberAndFish(Competition competition, User member, Fish fish);
}
