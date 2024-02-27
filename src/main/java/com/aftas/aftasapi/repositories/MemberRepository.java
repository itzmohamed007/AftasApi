package com.aftas.aftasapi.repositories;

import com.aftas.aftasapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<User, Integer> {

}
