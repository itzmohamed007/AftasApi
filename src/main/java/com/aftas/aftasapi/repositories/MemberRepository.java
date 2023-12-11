package com.aftas.aftasapi.repositories;

import com.aftas.aftasapi.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {

}
