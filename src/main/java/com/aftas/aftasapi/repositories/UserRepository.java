package com.aftas.aftasapi.repositories;

import com.aftas.aftasapi.models.User;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByEmail(String email);
    List<User> findAllByLockedTrue();
    @Modifying
    @Transactional
    @Query("update User u set u.locked = false where u.num = :num")
    void unlockAccount(Integer num);
}
