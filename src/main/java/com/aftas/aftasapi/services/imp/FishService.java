package com.aftas.aftasapi.services.imp;

import com.aftas.aftasapi.dtos.ReqFish;
import com.aftas.aftasapi.dtos.ResFish;
import com.aftas.aftasapi.dtos.ResLevel;
import com.aftas.aftasapi.dtos.ResMember;
import com.aftas.aftasapi.exceptions.FishNotFoundException;
import com.aftas.aftasapi.exceptions.LevelNotFoundException;
import com.aftas.aftasapi.exceptions.UniqueConstraintViolationException;
import com.aftas.aftasapi.models.Fish;
import com.aftas.aftasapi.models.Level;
import com.aftas.aftasapi.models.Member;
import com.aftas.aftasapi.repositories.FishRepository;
import com.aftas.aftasapi.services.IFishService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FishService implements IFishService {
    private final FishRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public ResFish read(String name) {
        Optional<Fish> fish = repository.findById(name);
        if(fish.isPresent()) {
            return modelMapper.map(fish.get(), ResFish.class);
        }
        throw new FishNotFoundException("Fish not found with name " + name);
    }

    @Override
    public List<ResFish> readAll() {
        List<ResFish> fishes = repository.findAll().stream()
                .map(fish -> modelMapper.map(fish, ResFish.class))
                .toList();
        if(fishes.isEmpty()) {
            throw new LevelNotFoundException("No Fishes were found");
        }
        return fishes;
    }

    @Override
    public Page<ResFish> readAllPaginated(Pageable pageable) {
        Page<Fish> paginatedFishes = repository.findAll(pageable);
        if(paginatedFishes.isEmpty()) {
            throw new LevelNotFoundException("No Fishes were found");
        }
        return paginatedFishes.map(fish -> modelMapper.map(fish, ResFish.class));
    }

    @Override
    public ResFish create(ReqFish reqFish) {
        try {
            Fish fish = modelMapper.map(reqFish, Fish.class);
            Fish savedFish = repository.save(fish);
            return modelMapper.map(savedFish, ResFish.class);
        } catch (DataIntegrityViolationException e) {
            throw new UniqueConstraintViolationException("Violated unique constraint (name)");
        }
    }

    @Override
    public ResFish update(ReqFish reqFish, String name) {
        Optional<Fish> fish = repository.findById(name);
        if (fish.isPresent()) {
            reqFish.setName(name);
            Fish createdFish = repository.save(modelMapper.map(reqFish, Fish.class));
            return modelMapper.map(createdFish, ResFish.class);
        }
        throw new FishNotFoundException("No fish was found with name " + name);
    }

    @Override
    public void delete(String name) {
        Optional<Fish> fish = repository.findById(name);
        if (fish.isPresent()) repository.deleteById(name);
        else throw new FishNotFoundException("No fish was found with name " + name);
    }
}
