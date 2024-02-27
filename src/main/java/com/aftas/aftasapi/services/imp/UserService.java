package com.aftas.aftasapi.services.imp;

import com.aftas.aftasapi.models.User;
import com.aftas.aftasapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> fetchAllLockedAccounts() {
        return userRepository.findAllByLockedTrue();
    }
}
