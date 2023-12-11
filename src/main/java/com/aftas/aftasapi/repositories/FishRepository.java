package com.aftas.aftasapi.repositories;

import com.aftas.aftasapi.models.Fish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishRepository extends JpaRepository<Fish, String> {

}
