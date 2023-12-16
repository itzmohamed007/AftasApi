package com.aftas.aftasapi.services.imp;

import com.aftas.aftasapi.dtos.ReqHunting;
import com.aftas.aftasapi.dtos.ResHunting;
import com.aftas.aftasapi.models.Hunting;
import com.aftas.aftasapi.repositories.HuntingRepository;
import com.aftas.aftasapi.services.IHuntingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HuntingService implements IHuntingService {
    private final HuntingRepository repository;
    private final ModelMapper modelMapper;
    @Override
    public ResHunting read(Integer integer) {

    }

    @Override
    public List<ResHunting> readAll() {
        return null;
    }

    @Override
    public Page<ResHunting> readAllPaginated(Pageable pageable) {
        return null;
    }

    @Override
    public ResHunting create(ReqHunting reqHunting) {
        return null;
    }

    @Override
    public ResHunting update(ReqHunting reqHunting, Integer id) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }
}
